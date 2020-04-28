package sdp.ch02.composition

trait FormalGreeting {
  def hello(): String
//  def getTime: String
}

trait InformalGreeting {
  def hello(): String
//  def getTime: Int
}

class Greeter extends FormalGreeting with InformalGreeting {
  override def hello(): String = "Good morning, sir/madam!"

//  override def getTime: Int = ???
}

object GreetUser {
  def main(args: Array[String]): Unit = {
    val greeter = new Greeter()
    println(greeter.hello())
  }
}
