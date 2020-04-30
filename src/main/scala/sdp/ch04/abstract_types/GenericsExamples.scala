package sdp.ch04.abstract_types

trait Adder {
  def sum[T](a: T, b: T)(implicit numeric: Numeric[T]): T = numeric.plus(a, b)
}

class Container[T](data: T) {
  def compare(other: T): Boolean = data.equals(other)
}

object GenericsExamples extends Adder {
  def main(args: Array[String]): Unit = {
    println(s"1 + 3 = ${sum(1, 3)}")
    println(s"1.2 + 6.7 = ${sum(1.2, 6.7)}")
//    println(s"abc + cde = ${sum("abc", "cde")}") // compilation fails because no implicits found for parameter: Numeric[String]

    val intContainer = new Container(10)
    println(s"Comparing with int: ${intContainer.compare(11)}")

    val stringContainer = new Container("some text")
    println(s"Comparing with string: ${stringContainer.compare("some text")}")
  }
}
