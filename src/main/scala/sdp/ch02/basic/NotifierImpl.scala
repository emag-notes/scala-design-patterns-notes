package sdp.ch02.basic

import sdp.ch02.common.Notifier

class NotifierImpl(val notificationMessage: String) extends Notifier {
  override def clear(): Unit = println("cleared")
}
