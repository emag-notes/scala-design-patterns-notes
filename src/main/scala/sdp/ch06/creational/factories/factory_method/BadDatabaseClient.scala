package sdp.ch06.creational.factories.factory_method

import sdp.ch06.creational.factories.{SimpleConnection, SimpleMySQLConnection, SimplePostgreSQLConnection}

abstract class BadDatabaseClient {
  def executeQuery(query: String): Unit = {
    val connection        = connect()
    val connectionPrinter = getConnectionPrinter
    connectionPrinter.printSimpleConnection(connection)
    connection.executeQuery(query)
  }

  protected def connect(): SimpleConnection
  protected def getConnectionPrinter: SimpleConnectionPrinter
}

class BadMySQLClient extends BadDatabaseClient {
  override protected def connect(): SimpleConnection                   = new SimpleMySQLConnection
  override protected def getConnectionPrinter: SimpleConnectionPrinter = new SimpleMySQLConnectionPrinter
}

class BadPostgreSQLClient extends BadDatabaseClient {
  override protected def connect(): SimpleConnection                   = new SimplePostgreSQLConnection
  override protected def getConnectionPrinter: SimpleConnectionPrinter = new SimplePostgreSQLConnectionPrinter
}
