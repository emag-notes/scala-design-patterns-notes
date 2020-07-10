package sdp.ch11.implicits

object ImplicitExamples {
  def main(args: Array[String]): Unit = {
    val number: Int = 7.6
    println(s"The integer value for 7.6 is $number")
    // prints HELLO!
    printAsciiString(List(72, 69, 76, 76, 79, 33))
  }

  def printAsciiString(s: String): Unit = println(s)
}
