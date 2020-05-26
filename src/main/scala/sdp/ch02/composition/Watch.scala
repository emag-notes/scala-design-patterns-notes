package sdp.ch02.composition

import sdp.ch02.common.{Alarm, Notifier}

class Watch(brand: String, initialTime: Long) {
  def getTime: Long = System.currentTimeMillis() - initialTime
}

object WatchUser {
  def main(args: Array[String]): Unit = {
    val expensiveWatch = new Watch("expensive brand", 1000L) with Alarm with Notifier {
      override def trigger(): String = "The alarm was triggered."

      override val notificationMessage: String = "Alarm is running!"
      override def clear(): Unit               = println("Alarm cleared.")
    }

    val cheapWatch = new Watch("cheap brand", 1000L) with Alarm {
      override def trigger(): String = "The alarm was triggered."
    }

    println(expensiveWatch.trigger())
    expensiveWatch.printNotification()
    println(s"The time is ${expensiveWatch.getTime}")
    expensiveWatch.clear()

    println(cheapWatch.trigger())
    println("Cheap watches cannot manually stop the alarm...")
  }
}

//object ReallyExpensiveWatchUser {
//  def main(args: Array[String]): Unit = {
//    val reallyExpensiveWatch = new Watch("really expensive brand", 1000L) with ConnectorWithHelper {
//      override def connect(): Unit = println("Connected with another connector.")
//      override def close(): Unit   = println("Closed with another connector.")
//    }
//
//    println("Using the really expensive watch.")
//    reallyExpensiveWatch.findDriver()
//    reallyExpensiveWatch.connect()
//    reallyExpensiveWatch.close()
//  }
//}

trait AlarmNotifier {
  this: Notifier =>

  def trigger(): String
}

object SelfTypeWatchUser {
  def main(args: Array[String]): Unit = {
    val watch = new Watch("alarm with notification", 1000L) with AlarmNotifier with Notifier {
      override def trigger(): String = "Alarm triggered."

      override val notificationMessage: String = "The notification."
      override def clear(): Unit               = println("Alarm cleared.")
    }
    println(watch.trigger())
    watch.printNotification()
    println(s"The time is ${watch.getTime}.")
    watch.clear()
  }
}
