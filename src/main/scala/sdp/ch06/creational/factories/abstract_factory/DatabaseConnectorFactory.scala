package sdp.ch06.creational.factories.abstract_factory

import sdp.ch06.creational.factories.{SimpleConnection, SimpleMySQLConnection, SimplePostgreSQLConnection}

trait DatabaseConnectorFactory {
  def connect(): SimpleConnection
}

class MySQLFactory extends DatabaseConnectorFactory {
  override def connect(): SimpleConnection = new SimpleMySQLConnection
}

class PostgreSQLFactory extends DatabaseConnectorFactory {
  override def connect(): SimpleConnection = new SimplePostgreSQLConnection
}
