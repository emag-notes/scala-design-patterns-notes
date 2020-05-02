package sdp.ch06.creational.factories

trait SimpleConnection {
  def getName: String
  def executeQuery(query: String): Unit
}

class SimpleMySQLConnection extends SimpleConnection {
  override def getName: String = "SimpleMySQLConnection"
  override def executeQuery(query: String): Unit =
    println(s"Executing the query '$query' the MySQL way.")
}

class SimplePostgreSQLConnection extends SimpleConnection {
  override def getName: String = "SimplePostgreSQLConnection"
  override def executeQuery(query: String): Unit =
    println(s"Executing the query '$query' the PostgreSQL way.")
}
