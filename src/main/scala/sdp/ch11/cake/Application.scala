package sdp.ch11.cake

object Application {
  import ApplicationComponentRegistry._

  def main(args: Array[String]): Unit = {
    migrationService.runMigration()
    println(dao.getPeople)
    println(dao.getClasses)
    println(dao.getPeopleInClass("Scala Design Patterns"))
    println(dao.getPeopleInClass("Mountain Biking"))
    println(
      s"Average age of everyone in Scala Design Patterns: ${userService.getAverageAgeOfUsersInClass("Scala Design Patterns")}"
    )
  }
}
