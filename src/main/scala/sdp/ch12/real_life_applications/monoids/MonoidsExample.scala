package sdp.ch12.real_life_applications.monoids

import scalaz._
import Scalaz._

object MonoidsExample {
  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6)
    println(s"The sum is: ${numbers.foldMap(identity)}")
    println(s"The product (6!): is: ${numbers.foldMap(Tags.Multiplication.apply)}")

    val strings = List("This is\n", "a list of\n", "strings!")
    println(strings.foldMap(identity)(stringConcatenation))
  }
}
