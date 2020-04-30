package sdp.ch04.self_types

trait Persister[T] {
  this: Database[T] with Mystery =>
  def persist(data: T): Unit = {
    println("Calling persist.")
    save(data)
    add()
  }
}

trait Database[T] {
  def save(data: T)
}

trait MemoryDatabase[T] extends Database[T] {
  import scala.collection.mutable
  val db: mutable.ArrayDeque[T] = mutable.ArrayDeque.empty

  override def save(data: T): Unit = {
    println("Saving to in memory database.")
    db.+=:(data)
  }
}

trait FileDatabase[T] extends Database[T] {
  override def save(data: T): Unit = println("Saving to file.")
}

trait History {
  def add(): Unit = println("Action added to history.")
}

trait Mystery {
  def add(): Unit = println("Mystery added!")
}

class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery {
  override def add(): Unit = super[History].add()
}
class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery {
  override def add(): Unit = super[Mystery].add()
}

object PersisterExample {
  def main(args: Array[String]): Unit = {
    val memoryIntPersister  = new MemoryPersister[Int]
    val fileStringPersister = new FilePersister[String]

    memoryIntPersister.persist(100)
    memoryIntPersister.persist(123)

    fileStringPersister.persist("Something")
    fileStringPersister.persist("Something else")
  }
}
