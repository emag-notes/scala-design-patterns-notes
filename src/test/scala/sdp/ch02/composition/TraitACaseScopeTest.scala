package sdp.ch02.composition

import org.scalatest.flatspec.AnyFlatSpec

class TraitACaseScopeTest extends AnyFlatSpec {
  "hello" should "great properly." in new A {
    assert(hello() === "Hello, I am trait A!")
  }

  "pass" should "return the right string with the number." in new A {
    assert(pass(10) === "Trait A said: 'You passed 10.'")
  }

  it should "be correct also for negative values." in new A {
    assert(pass(-10) === "Trait A said: 'You passed -10.'")
  }
}
