package sdp.ch08.behavioral.null_object

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.{ConcurrentLinkedDeque, TimeUnit}

import scala.util.Random

class DataGenerator extends Runnable {
  val MaxVal  = 10
  val MaxTime = 10000

  private val isStop = new AtomicBoolean(false)

  private val queue = new ConcurrentLinkedDeque[Int]()

  override def run(): Unit = {
    val random = new Random()
    while (!isStop.get()) {
      TimeUnit.MILLISECONDS.sleep(random.nextInt(MaxTime))
      queue.add(random.nextInt(MaxVal))
    }
  }

  def getMessage: Option[Message] =
    Option(queue.poll()).map(Message)

  def requestStop(): Unit = isStop.set(true)
}
