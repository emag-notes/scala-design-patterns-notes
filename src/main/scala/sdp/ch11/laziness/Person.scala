package sdp.ch11.laziness

final case class Person(name: String, age: Int)

object Person {
  def getFromDatabase: List[Person] = {
    // simulate we're getting people from database by sleeping
    println("Retrieving people...")
    Thread.sleep(3000)
    List(
      Person("Ivan", 26),
      Person("Maria", 26),
      Person("John", 25)
    )
  }

  def printPeopleBad(people: => List[Person]): Unit = {
    println(s"Print first time: $people")
    println(s"Print second time: $people")
  }

  def printPeopleGood(people: => List[Person]): Unit = {
    lazy val peopleCopy = people
    println(s"Print first time: $peopleCopy")
    println(s"Print second time: $peopleCopy")
  }
}

object Example {
  def main(args: Array[String]): Unit = {
    import Person._

    println("Now printing bad.")
    printPeopleBad(getFromDatabase)
    println("Now printing good.")
    printPeopleGood(getFromDatabase)
  }
}
