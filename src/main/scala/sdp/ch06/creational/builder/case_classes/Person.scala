package sdp.ch06.creational.builder.case_classes

final case class Person(firstName: String = "", lastName: String = "", age: Int = 0)

object PersonCaseClassExample {
  def main(args: Array[String]): Unit = {
    val person1 = Person(
      firstName = "Ivan",
      lastName = "Nikolov",
      age = 26
    )

    val person2 = Person(
      firstName = "John"
    )

    println(s"Person 1: $person1")
    println(s"Person 2: $person2")
  }
}
