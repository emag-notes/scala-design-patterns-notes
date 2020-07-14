package sdp.ch12.real_life_applications.monads.model

final case class Person(name: String, age: Int)

object Person {
  def fromArray(arr: Array[String]): Option[Person] =
    arr match {
      case Array(name, age) => Some(Person(name, age.toInt))
      case _                => None
    }
}
