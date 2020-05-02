package sdp.ch06.creational.builder.type_safe.case_require

import scala.util.control.NonFatal

final case class Person(
    firstName: String = "",
    lastName: String = "",
    age: Int = 0
) {
  require(firstName.nonEmpty, "First name is required.")
  require(lastName.nonEmpty, "Last name is required.")
}

object PersonCaseClassRequireExample {
  def main(args: Array[String]): Unit = {
    val person1 = Person(
      firstName = "Ivan",
      lastName = "Nikolov",
      age = 26
    )
    println(s"Person 1: $person1")

    try {
      val person2 = Person(firstName = "John")
      println(s"Person 2: $person2")
    } catch {
      case NonFatal(ex) =>
        ex.printStackTrace()
    }
  }
}
