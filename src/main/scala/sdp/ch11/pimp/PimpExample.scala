package sdp.ch11.pimp

import sdp.ch11.pimp.model.Person

object PimpExample {
  def main(args: Array[String]): Unit = {
    println(s"Is 'test' all upper case: ${"test".isAllUpperCase}")
    println(s"Is 'Test' all upper case: ${"Test".isAllUpperCase}")
    println(s"Is 'TESt' all upper case: ${"TESt".isAllUpperCase}")
    println(s"Is 'TEST' all upper case: ${"TEST".isAllUpperCase}")
  }
}

object PimpExample2 {
  def main(args: Array[String]): Unit = {
    val people = List(
      Person("Ivan", 26),
      Person("Maria", 26),
      Person("John", 25)
    )

    people.saveToDatabase()
  }
}
