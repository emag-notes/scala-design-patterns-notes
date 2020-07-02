package sdp.ch10.monads

final case class ListWrapper(list: List[Int]) {
  // just wrap
  def map[B](f: Int => B): List[B] = list.map(f)

  // just wrap
  def flatMap[B](f: Int => IterableOnce[B]): List[B] = list.flatMap(f)
}

object ForComprehensionWithLists {
  def main(args: Array[String]): Unit = {
    val l1 = List(1, 2, 3, 4)
    val l2 = List(5, 6, 7, 8)

    val result1 = for {
      x <- l1
      y <- l2
    } yield x * y
    // same as
    val result2 = l1.flatMap(x => l2.map(x * _))

    println(s"The result1 is: $result1")
    println(s"The result2 is: $result2")
  }
}

object ForComprehensionWithObjects {
  def main(args: Array[String]): Unit = {
    val l1 = ListWrapper(List(1, 2, 3, 4))
    val l2 = ListWrapper(List(5, 6, 7, 8))

    val result1 = for {
      x <- l1
      y <- l2
    } yield x * y
    // same as
    val result2 = l1.flatMap(x => l2.map(x * _))

    println(s"The result1 is: $result1")
    println(s"The result2 is: $result2")
  }
}
