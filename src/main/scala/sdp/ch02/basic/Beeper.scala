package sdp.ch02.basic

trait Beeper {
  def beep(times: Int): Unit = (1 to times).foreach(i => println(s"Beep number: $i"))
}

object BeeperRunner {
  val Times = 10

  def main(args: Array[String]): Unit = {
    val beeper = new Beeper {}
    beeper.beep(Times)
  }
}
