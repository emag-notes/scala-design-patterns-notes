package sdp.ch12.job_scheduler.scheduler.actors

import akka.actor.Actor
import com.typesafe.scalalogging.LazyLogging
import sdp.ch12.job_scheduler.scheduler.actors.messages.{Done, Work}
import sdp.ch12.job_scheduler.scheduler.config.job.{Console, Sql}
import sdp.ch12.job_scheduler.scheduler.dao.DaoService

import scala.util.Using
import sys.process._

class Worker(daoService: DaoService) extends Actor with LazyLogging {

  private def doWork(work: Work): Unit = {
    work.jobType match {
      case Console =>
        val result = work.command.! // note - the ! are different methods
        sender() ! Done(work.name, work.command, work.jobType, result == 0)
      case Sql =>
        Using(daoService.getConnection) { connection =>
          val statement = connection.prepareStatement(work.command)
          val result: List[String] = daoService.executeSelect(statement) { rs =>
            val metadata   = rs.getMetaData
            val numColumns = metadata.getColumnCount
            daoService.readResultSet(rs) { row =>
              (1 to numColumns)
                .map(row.getObject)
                .mkString("\t")
            }
          }
          logger.info("Sql query results:")
          result.foreach(logger.info(_))
          sender() ! Done(work.name, work.command, work.jobType, success = true)
        }
    }
  }

  override def receive: Receive = {
    case w: Work => doWork(w)
  }
}
