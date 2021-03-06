package sdp.ch03.unification.functions

class FunctionLiterals {
  val sum: (Int, Int) => Int = (a: Int, b: Int) => a + b

  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int = f(a, b)
}

object FunctionLiterals {
  def main(args: Array[String]): Unit = {
    val obj = new FunctionLiterals
    println(s"3 + 9 = ${obj.sum(3, 9)}")
    println(s"Calling run operation: ${obj.runOperation(obj.sum, 10, 20)}")
    println(s"Using Math.max: ${obj.runOperation(Math.max, 10, 20)}")
  }
}
