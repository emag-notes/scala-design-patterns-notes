package sdp.ch08.behavioral.state

import sdp.ch08.behavioral.state.model.MediaPlayer

sealed trait State[T] {
  def press(context: T): Unit
}

object Playing extends State[MediaPlayer] {
  override def press(context: MediaPlayer): Unit = {
    println("Pressing pause.")
    context.setState(Paused)
  }
}

object Paused extends State[MediaPlayer] {
  override def press(context: MediaPlayer): Unit = {
    println("Pressing play.")
    context.setState(Playing)
  }
}
