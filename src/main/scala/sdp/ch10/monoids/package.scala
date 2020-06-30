package sdp.ch10

package object monoids {
  val intAddition: Monoid[Int] = new Monoid[Int] {
    override def op(l: Int, r: Int): Int = l + r
    override def zero: Int               = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    override def op(l: Int, r: Int): Int = l * r
    override def zero: Int               = 1
  }

  val stringConcatenation: Monoid[String] = new Monoid[String] {
    override def op(l: String, r: String): String = l + r
    override def zero: String                     = ""
  }

  def compose[T, Y](a: Monoid[T], b: Monoid[Y]): Monoid[(T, Y)] = new Monoid[(T, Y)] {
    override def op(l: (T, Y), r: (T, Y)): (T, Y) = (a.op(l._1, r._1), b.op(l._2, r._2))
    override def zero: (T, Y)                     = (a.zero, b.zero)
  }

  def mapMerge[K, V](a: Monoid[V]): Monoid[Map[K, V]] = new Monoid[Map[K, V]] {
    override def op(l: Map[K, V], r: Map[K, V]): Map[K, V] =
      (l.keySet ++ r.keySet).foldLeft(zero) {
        case (res, key) =>
          res.updated(key, a.op(l.getOrElse(key, a.zero), r.getOrElse(key, a.zero)))
      }
    override def zero: Map[K, V] = Map()
  }

  object MonoidOperations {
    def fold[T](list: List[T], m: Monoid[T]): T = foldMap(list, m)(identity)

    def foldMap[T, Y](list: List[T], m: Monoid[Y])(f: T => Y): Y =
      list.foldLeft(m.zero) {
        case (t, y) => m.op(t, f(y))
      }

    def balancedFold[T, Y](list: IndexedSeq[T], m: Monoid[Y])(f: T => Y): Y =
      if (list.isEmpty) {
        m.zero
      } else if (list.length == 1) {
        f(list(0))
      } else {
        val (left, right) = list.splitAt(list.length / 2)
        m.op(balancedFold(left, m)(f), balancedFold(right, m)(f))
      }

    def foldPar[T](list: List[T], m: Monoid[T]): T =
      foldMapPar(list, m)(identity)

    def foldMapPar[T, Y](list: List[T], m: Monoid[Y])(f: T => Y): Y = {
      import scala.collection.parallel.CollectionConverters._

      list.par.foldLeft(m.zero) {
        case (t, y) => m.op(t, f(y))
      }
    }
  }
}
