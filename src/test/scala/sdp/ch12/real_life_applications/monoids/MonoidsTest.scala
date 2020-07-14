package sdp.ch12.real_life_applications.monoids

import scalaz._
import org.scalacheck.Arbitrary
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.Checkers
import scalaz.scalacheck.ScalazProperties._

class MonoidsTest extends AnyFlatSpec with Matchers with Checkers {
  implicit def arbString(implicit ev: Arbitrary[String]): Arbitrary[String] =
    Arbitrary { ev.arbitrary.map(identity) }

  "stringConcatenation monoid" should "satisfy the identity rule." in {
    monoid.laws[String](stringConcatenation, Equal.equalA[String], arbString).check()
  }
}
