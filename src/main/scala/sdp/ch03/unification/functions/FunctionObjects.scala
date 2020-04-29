package sdp.ch03.unification.functions

class SumFunction extends Function2[Int, Int, Int] {
  override def apply(v1: Int, v2: Int): Int = v1 + v2
}

class FunctionObjects {
  val sum: SumFunction = new SumFunction

  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int = f(a, b)
}
object FunctionObjects {
  def main(args: Array[String]): Unit = {
    val obj = new FunctionObjects
    println(s"3 + 9 = ${obj.sum(3, 9)}")
    println(s"Calling run operation: ${obj.runOperation(obj.sum, 10, 20)}")
    println(s"Using Math.max: ${obj.runOperation(Math.max, 10, 20)}")
  }
}
