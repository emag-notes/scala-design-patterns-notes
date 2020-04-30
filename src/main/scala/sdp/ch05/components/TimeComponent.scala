package sdp.ch05.components

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import sdp.ch05.components.base.Time

trait TimeComponent {
  val time: Time

  class TimeImpl extends Time {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    override def getTime: String = s"The time is: ${LocalDateTime.now().format(formatter)}"
  }
}
