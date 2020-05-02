package sdp.ch06.creational.factories.abstract_factory

class DatabaseClient(connectorFactory: DatabaseConnectorFactory) {
  def executeQuery(query: String): Unit = {
    val connection = connectorFactory.connect()
    connection.executeQuery(query)
  }
}
