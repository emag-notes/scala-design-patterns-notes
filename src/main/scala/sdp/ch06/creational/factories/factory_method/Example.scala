package sdp.ch06.creational.factories.factory_method

object Example {
  def main(args: Array[String]): Unit = {
    val mySQLClient      = new MySQLClient
    val postgreSQLClient = new PostgreSQLClient

    mySQLClient.executeQuery("SELECT * FROM users")
    postgreSQLClient.executeQuery("SELECT * FROM employees")
  }
}
