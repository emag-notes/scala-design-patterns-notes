package sdp.ch11.cake

import java.sql.Connection

import org.h2.jdbcx.JdbcConnectionPool

trait DatabaseService {
  val dbDriver: String
  val connectionString: String
  val username: String
  val password: String

  val ds: JdbcConnectionPool = {
    JdbcConnectionPool.create(connectionString, username, password)
  }

  def getConnection: Connection = ds.getConnection
}

trait DatabaseComponent {
  val databaseService: DatabaseService

  class H2DatabaseService(val connectionString: String, val username: String, val password: String)
      extends DatabaseService {
    override val dbDriver: String = "org.h2.Driver"
  }
}
