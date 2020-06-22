package sdp.ch08.behavioral.interpreter

import java.util.StringTokenizer

import scala.collection.mutable
import scala.jdk.CollectionConverters._

class RPNParser {
  def parse(expression: String): Expression = {
    val tokenizer = new StringTokenizer(expression)
    tokenizer.asScala
      .foldLeft(mutable.Stack[Expression]()) {
        case (result, token) =>
          val item = Expression(token.toString, result.pop(), result.pop())
          item.foreach(result.push)
          result
      }
      .pop()
  }
}

class RPNInterpreter {
  def interpret(expression: Expression): Int = expression.interpret()
}

object RPNExample {
  def main(args: Array[String]): Unit = {
    val expr1 = "1 2 + 3 * 9 10 + -"
    val expr2 = "1 2 3 4 5 * * - +"
    val expr3 = "12 -" // invalid

    val parser      = new RPNParser
    val interpreter = new RPNInterpreter

    println(s"The result of '$expr1' is: ${interpreter.interpret(parser.parse(expr1))}'")
    println(s"The result of '$expr2' is: ${interpreter.interpret(parser.parse(expr2))}'")

    try {
      println(s"The result of '$expr3' is: ${interpreter.interpret(parser.parse(expr3))}'")
    } catch {
      case _: Throwable => println(s"'$expr3' is invalid.")
    }
  }
}
