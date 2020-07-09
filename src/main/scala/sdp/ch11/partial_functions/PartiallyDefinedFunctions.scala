package sdp.ch11.partial_functions

object PartiallyDefinedFunctions {
  val squareRoot: PartialFunction[Int, Double] = {
    case a if a >= 0 => Math.sqrt(a)
  }

  val square: PartialFunction[Int, Double] = {
    case a if a < 0 => Math.pow(a, 2)
  }
}

object PartiallyDefinedExample {
  import PartiallyDefinedFunctions._

  def main(args: Array[String]): Unit = {
    val items = List(-1, 10, 11, -36, 36, -49, 49, 81)
    println(s"Can we calculate a root for -10: ${squareRoot.isDefinedAt(-10)}")
    println(s"Square roots: ${items.collect(squareRoot)}")
    println(s"Square roots or squares: ${items.collect(squareRoot orElse square)}")
  }
}
