package sdp.ch08.behavioral.interpreter

trait Expression {
  def interpret(): Int
}

final case class Number(n: Int) extends Expression {
  override def interpret(): Int = n
}

final case class Add(right: Expression, left: Expression) extends Expression {
  override def interpret(): Int = left.interpret() + right.interpret()
}

final case class Subtract(right: Expression, left: Expression) extends Expression {
  override def interpret(): Int = left.interpret() - right.interpret()
}

final case class Multiply(right: Expression, left: Expression) extends Expression {
  override def interpret(): Int = left.interpret() * right.interpret()
}

object Expression {
  def apply(operator: String, left: => Expression, right: => Expression): Option[Expression] =
    operator match {
      case "+"                    => Some(Add(right, left))
      case "-"                    => Some(Subtract(right, left))
      case "*"                    => Some(Multiply(right, left))
      case i if i.matches("\\d+") => Some(Number(i.toInt))
      case _                      => None
    }
}
