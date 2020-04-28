package sdp.ch02.composition

class TraitATest extends org.scalatest.flatspec.AnyFlatSpec with A {
  "hello" should "greet properly." in {
    assert(hello() === "Hello, I am trait A!")
  }

  "pass" should "return the right string with the number." in {
    assert(pass(10) === "Trait A said: 'You passed 10.'")
  }

  it should "be correct also for negative values." in {
    assert(pass(-10) === "Trait A said: 'You passed -10.'")
  }
}
