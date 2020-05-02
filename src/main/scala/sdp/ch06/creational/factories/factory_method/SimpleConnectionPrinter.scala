package sdp.ch06.creational.factories.factory_method

import sdp.ch06.creational.factories.SimpleConnection

trait SimpleConnectionPrinter {
  def printSimpleConnection(connection: SimpleConnection): Unit
}

class SimpleMySQLConnectionPrinter extends SimpleConnectionPrinter {
  override def printSimpleConnection(connection: SimpleConnection): Unit =
    println(s"I require a MySQL connection. It is: ${connection.getName}")
}

class SimplePostgreSQLConnectionPrinter extends SimpleConnectionPrinter {
  override def printSimpleConnection(connection: SimpleConnection): Unit =
    println(s"I require a PostgreSQL connection. It is: ${connection.getName}")
}
