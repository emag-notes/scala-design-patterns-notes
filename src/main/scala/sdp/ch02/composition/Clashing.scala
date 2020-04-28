package sdp.ch02.composition

trait A {
  def hello(): String      = "Hello, I am trait A!"
  def pass(a: Int): String = s"Trait A said: 'You passed $a.'"
}

trait B {
  def hello(): String = "Hello, I am trait B!"
}

object Clashing extends A with B {
  override def hello(): String = super[A].hello()

  def main(args: Array[String]): Unit = {
    println(hello())
  }
}
