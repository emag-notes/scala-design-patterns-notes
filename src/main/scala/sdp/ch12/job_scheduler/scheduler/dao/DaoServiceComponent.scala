package sdp.ch12.job_scheduler.scheduler.dao

import java.sql.{Connection, PreparedStatement, ResultSet, SQLException}

import scala.util.Using

trait DaoService {
  def getConnection: Connection

  def executeSelect[T](preparedStatement: PreparedStatement)(f: ResultSet => List[T]): List[T] =
    Using(preparedStatement) { stmt => f(stmt.executeQuery()) }.fold(
      err => throw new SQLException(err),
      suc => suc
    )

  def readResultSet[T](rs: ResultSet)(f: ResultSet => T): List[T] =
    Iterator
      .continually((rs.next(), rs))
      .takeWhile(_._1)
      .map {
        case (_, row) =>
          f(row)
      }
      .toList
}

trait DaoServiceComponent {
  this: DatabaseServiceComponent =>

  val daoService: DaoService

  class DaoServiceImpl extends DaoService {
    override def getConnection: Connection = databaseService.getConnection
  }
}
