package sdp.ch08.behavioral.memento

import scala.collection.mutable

trait Memento[T] {
  protected val state: T

  def getState: T = state
}

trait Caretaker[T] {
  val status: mutable.Stack[Memento[T]] = mutable.Stack[Memento[T]]()
}

trait Originator[T] {
  def createMemento: Memento[T]
  def restore(memento: Memento[T]): Unit
}
