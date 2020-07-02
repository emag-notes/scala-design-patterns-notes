package sdp.ch10.monads

trait Option[A] extends Monad[A]

final case class Some[A](a: A) extends Option[A] {
  override def unit[Y](value: Y): Monad[Y]            = Some(value)
  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = f(a)
}

final case class None[A]() extends Option[A] {
  override def unit[Y](value: Y): Monad[Y]            = None()
  override def flatMap[Y](f: A => Monad[Y]): Monad[Y] = None()
}

final case class Doer() {
  def getAlgorithm(isFail: Boolean): Algorithm =
    if (isFail) {
      null
    } else {
      Algorithm()
    }
}

final case class Algorithm() {
  def getImplementation(isFail: Boolean, left: Int, right: Int): Implementation =
    if (isFail) {
      null
    } else {
      Implementation(left, right)
    }
}

final case class Implementation(left: Int, right: Int) {
  def compute: Int = left + right
}

object NoMonadExample {
  def main(args: Array[String]): Unit = {
    println(s"The result is: ${compute(Doer(), 10, 16)}")
  }

  def compute(doer: Doer, left: Int, right: Int): Int =
    if (doer != null) {
      val algorithm = doer.getAlgorithm(false)
      if (algorithm != null) {
        val implementation = algorithm.getImplementation(false, left, right)
        if (implementation != null) {
          implementation.compute
        } else {
          -1
        }
      } else {
        -1
      }
    } else {
      -1
    }
}

final case class Doer_V2() {
  def getAlgorithm(isFail: Boolean): Option[Algorithm_V2] =
    if (isFail) {
      None()
    } else {
      Some(Algorithm_V2())
    }
}

final case class Algorithm_V2() {
  def getImplementation(isFail: Boolean, left: Int, right: Int): Option[Implementation] =
    if (isFail) {
      None()
    } else {
      Some(Implementation(left, right))
    }
}

object MonadExample {
  def main(args: Array[String]): Unit = {
    println(s"The reuslt is: ${compute(Some(Doer_V2()), 10, 16)}")
  }

  def compute(doer: Option[Doer_V2], left: Int, right: Int): Monad[Int] =
    for {
      d <- doer
      a <- d.getAlgorithm(false)
      i <- a.getImplementation(false, left, right)
    } yield i.compute
}
