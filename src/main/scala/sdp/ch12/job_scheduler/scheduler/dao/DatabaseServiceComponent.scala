package sdp.ch12.job_scheduler.scheduler.dao

import java.sql.Connection

import javax.sql.DataSource
import org.h2.jdbcx.JdbcConnectionPool
import sdp.ch12.job_scheduler.scheduler.config.app.AppConfigComponent

trait DatabaseService {
  val dbDriver: String
  val connectionString: String
  val username: String
  val password: String
  val ds: DataSource

  def getConnection: Connection = ds.getConnection()
}

trait DatabaseServiceComponent {
  this: AppConfigComponent =>

  val databaseService: DatabaseService

  class H2DatabaseService extends DatabaseService {
    override val dbDriver: String         = "org.h2.Driver"
    override val connectionString: String = appConfigService.dbConnectionString
    override val username: String         = appConfigService.dbUsername
    override val password: String         = appConfigService.dbPassword
    override val ds: DataSource           = JdbcConnectionPool.create(connectionString, username, password)
  }
}
