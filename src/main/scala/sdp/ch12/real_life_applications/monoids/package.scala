package sdp.ch12.real_life_applications

import scalaz.Monoid

package object monoids {
  // Int addition and int multiplication exist already,
  // so we weill show them in an example.

  val stringConcatenation: Monoid[String] = new Monoid[String] {
    override def zero: String = ""

    override def append(f1: String, f2: => String): String = f1 + f2
  }
}
