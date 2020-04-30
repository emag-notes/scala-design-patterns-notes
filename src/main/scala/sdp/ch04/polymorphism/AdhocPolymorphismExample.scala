package sdp.ch04.polymorphism

trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)

  implicit val int2Adder: Adder[Int] = (a: Int, b: Int) => a + b

  implicit val string2Adder: Adder[String] = (a: String, b: String) => s"$a concatenated with $b"
}

object AdhocPolymorphismExample {
  import Adder._

  def main(args: Array[String]): Unit = {
    println(s"The sum of 1 + 2 is ${sum(1, 2)}")
    println(s"The sum of abc + def is ${sum("abc", "def")}")
  }
}
