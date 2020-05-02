package sdp.ch06.creational.factories.factory_method

object BadExample {
  def main(args: Array[String]): Unit = {
    val mySQLClient      = new BadMySQLClient
    val postgreSQLClient = new BadPostgreSQLClient

    mySQLClient.executeQuery("SELECT * FROM users")
    postgreSQLClient.executeQuery("SELECT * FROM employees")
  }
}
