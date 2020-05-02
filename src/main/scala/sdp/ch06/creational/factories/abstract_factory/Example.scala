package sdp.ch06.creational.factories.abstract_factory

object Example {
  def main(args: Array[String]): Unit = {
    val mySQLClient      = new DatabaseClient(new MySQLFactory)
    val postgreSQLClient = new DatabaseClient(new PostgreSQLFactory)

    mySQLClient.executeQuery("SELECT * FROM users")
    postgreSQLClient.executeQuery("SELECT * FROM employees")
  }
}
