package sdp.ch06.creational.builder.type_safe

class Person(
    val firstName: String,
    val lastName: String,
    val age: Int
)

sealed trait BuildStep
sealed trait HasFirstName extends BuildStep
sealed trait HasLastName  extends BuildStep

class PersonBuilder[PassedStep <: BuildStep] private (
    var firstName: String,
    var lastName: String,
    var age: Int
) {
  protected def this() = this(firstName = "", lastName = "", age = 0)
  protected def this(pb: PersonBuilder[_]) = this(
    firstName = pb.firstName,
    lastName = pb.lastName,
    age = pb.age
  )

  def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
    this.firstName = firstName
    new PersonBuilder[HasFirstName](this)
  }

  def setLastName(lastName: String)(implicit ev: PassedStep =:= HasFirstName): PersonBuilder[HasLastName] = {
    this.lastName = lastName
    new PersonBuilder[HasLastName](this)
  }

  def setAge(age: Int): PersonBuilder[PassedStep] = {
    this.age = age
    this
  }

  def build()(implicit ev: PassedStep =:= HasLastName): Person = new Person(
    firstName,
    lastName,
    age
  )
}

object PersonBuilder {
  def apply() = new PersonBuilder[BuildStep]()
}

object PersonBuilderTypeSafeExample {
  def main(args: Array[String]): Unit = {
    val person = PersonBuilder()
      .setFirstName("Ivan")
      .setLastName("Nikolov")
      .setAge(26)
      .build()

    println(s"Person: ${person.firstName} ${person.lastName}, Age: ${person.age}")
  }
}
