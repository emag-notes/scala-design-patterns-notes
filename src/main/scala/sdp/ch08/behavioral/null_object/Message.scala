package sdp.ch08.behavioral.null_object

import java.util.concurrent.TimeUnit

import scala.util.Random

case class Message(number: Int) {
  def print(): String = s"This is a message with number: $number."
}

object MessageExample {
  val TimesToTry = 10
  val MaxTime    = 5000

  def main(args: Array[String]): Unit = {
    val generator = new DataGenerator
    new Thread(generator).start()

    val random = new Random()
    (0 to TimesToTry).foreach { _ =>
      TimeUnit.MILLISECONDS.sleep(random.nextInt(MaxTime))
      println("Getting next message...")
      generator.getMessage.foreach(m => println(m.print()))
    }

    generator.requestStop()
  }
}
