package sdp.ch06.creational.factories.factory_method

import sdp.ch06.creational.factories.{SimpleConnection, SimpleMySQLConnection, SimplePostgreSQLConnection}

abstract class DatabaseClient {
  def executeQuery(query: String): Unit = {
    val connection = connect()
    connection.executeQuery(query)
  }

  protected def connect(): SimpleConnection
}

class MySQLClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimpleMySQLConnection
}

class PostgreSQLClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimplePostgreSQLConnection
}
