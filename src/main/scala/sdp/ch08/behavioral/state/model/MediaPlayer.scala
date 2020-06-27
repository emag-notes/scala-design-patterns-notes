package sdp.ch08.behavioral.state.model

import sdp.ch08.behavioral.state.{Paused, State}

final case class MediaPlayer() {
  private var state: State[MediaPlayer] = Paused

  def pressPlayOrPauseButton(): Unit = state.press(this)

  def setState(state: State[MediaPlayer]): Unit =
    this.state = state
}

object MediaPlayerExample {
  def main(args: Array[String]): Unit = {
    val player = MediaPlayer()

    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
  }
}
