package sdp.ch03.unification.adts

case class Point(x: Double, y: Double)

sealed trait Shape
case class Circle(centre: Point, radius: Double)                    extends Shape
case class Rectangle(topLeft: Point, height: Double, width: Double) extends Shape

object Shape {
  def area(shape: Shape): Double =
    shape match {
      case Circle(_, radius)  => Math.PI * Math.pow(radius, 2)
      case Rectangle(_, h, w) => h * w
    }
}
object ShapeDemo {
  def main(args: Array[String]): Unit = {
    val circle = Circle(Point(1, 2), 2.5)
    val rect   = Rectangle(Point(6, 7), 5, 6)

    println(s"The circle area is: ${Shape.area(circle)}")
    println(s"The rectangle area is: ${Shape.area(rect)}")
  }
}
