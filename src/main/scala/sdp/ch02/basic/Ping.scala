package sdp.ch02.basic

trait Ping {
  def ping(): Unit = println("ping")
}

trait Pong {
  def pong(): Unit = println("pong")
}

trait PingPong extends Ping with Pong {
  def pingPong(): Unit = {
    ping()
    pong()
  }
}

object PingPongRunner extends PingPong {
  def main(args: Array[String]): Unit = pingPong()
}
