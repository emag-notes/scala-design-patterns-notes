package sdp.ch02.common

trait Notifier {
  val notificationMessage: String

  def printNotification(): Unit = {
    println(notificationMessage)
  }

  def clear(): Unit
}
