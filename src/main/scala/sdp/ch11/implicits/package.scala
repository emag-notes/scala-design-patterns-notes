package sdp.ch11

package object implicits {
  implicit def doubleToInt(a: Double): Int =
    Math.round(a).toInt

  implicit def intsToString(ints: List[Int]): String =
    ints.map(_.toChar).mkString
}
