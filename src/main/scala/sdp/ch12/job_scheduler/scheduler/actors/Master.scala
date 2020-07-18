package sdp.ch12.job_scheduler.scheduler.actors

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, Cancellable, Props}
import akka.routing.RoundRobinPool
import com.typesafe.scalalogging.LazyLogging
import sdp.ch12.job_scheduler.scheduler.actors.messages.{Done, Schedule, Work}
import sdp.ch12.job_scheduler.scheduler.config.job.{Daily, Hourly}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

class Master(numWorkers: Int, actorFactory: ActorFactory) extends Actor with LazyLogging {
  val cancelables: ListBuffer[Cancellable] = ListBuffer[Cancellable]()

  val router: ActorRef = context.actorOf(
    Props(actorFactory.createWorkerActor()).withRouter(RoundRobinPool(numWorkers)),
    "scheduler-master-worker-router"
  )

  override def receive: Receive = {
    case Done(name, command, _, success) =>
      if (success) {
        logger.info(s"Successfully completed $name ($command).")
      } else {
        logger.error(s"Failure! Command $name ($command) returned a non-zero result code.")
      }
    case Schedule(configs) =>
      configs.foreach { config =>
        val cancellable = this.context.system.scheduler.scheduleWithFixedDelay(
          config.timeOptions.getInitialDelay(LocalDateTime.now(), config.frequency),
          config.frequency match {
            case Hourly => Duration.create(1, TimeUnit.HOURS)
            case Daily  => Duration.create(1, TimeUnit.DAYS)
          },
          router,
          Work(config.name, config.command, config.jobType)
        )
        cancellable +: cancelables
        logger.info(s"Scheduled: $config")
      }
  }

  override def postStop(): Unit = cancelables.foreach(_.cancel())
}
