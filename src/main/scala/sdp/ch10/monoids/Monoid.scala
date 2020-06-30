package sdp.ch10.monoids

trait Monoid[T] {
  def op(l: T, r: T): T
  def zero: T
}
