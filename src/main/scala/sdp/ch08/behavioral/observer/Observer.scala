package sdp.ch08.behavioral.observer

import scala.collection.mutable.ListBuffer

trait Observer[T] {
  def handleUpdate(subject: T): Unit
}

trait Observable[T] {
  this: T =>

  private val observers = ListBuffer[Observer[T]]()

  def addObserver(observer: Observer[T]): Unit = observers.prepend(observer)

  def notifyObservers(): Unit = observers.foreach(_.handleUpdate(this))
}
