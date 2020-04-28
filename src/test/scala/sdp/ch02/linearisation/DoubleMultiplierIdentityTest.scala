package sdp.ch02.linearisation

import org.scalatest.flatspec.AnyFlatSpec

class DoubleMultiplierIdentityTest extends AnyFlatSpec {
  class DoubleMultiplierIdentityClass extends DoubleMultiplierIdentity

  val instance = new DoubleMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    assert(instance.identity === 2)
  }
}
