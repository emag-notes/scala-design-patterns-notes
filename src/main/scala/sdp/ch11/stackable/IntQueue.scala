package sdp.ch11.stackable

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int): Unit
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = buf += x
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(2 * x)
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

object IntQueueExample {
  def main(args: Array[String]): Unit = {
    val queue = new BasicIntQueue with Filtering with Incrementing
    queue.put(-1)
    queue.put(0)
    queue.put(1)
    println(queue.get())
    println(queue.get())
    println(queue.get())
  }
}
