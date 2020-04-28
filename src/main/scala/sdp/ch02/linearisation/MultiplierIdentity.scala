package sdp.ch02.linearisation

class MultiplierIdentity {
  def identity: Int = 1
}

trait DoubleMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 2 * super.identity
}

trait TripleMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 3 * super.identity
}

// first Doubled, then Tripled
class ModifiedIdentity1 extends DoubleMultiplierIdentity with TripleMultiplierIdentity

class ModifiedIdentity2 extends DoubleMultiplierIdentity with TripleMultiplierIdentity {
  override def identity: Int = super[DoubleMultiplierIdentity].identity
}

class ModifiedIdentity3 extends DoubleMultiplierIdentity with TripleMultiplierIdentity {
  override def identity: Int = super[TripleMultiplierIdentity].identity
}

// first Tripled, then Doubled
class ModifiedIdentity4 extends TripleMultiplierIdentity with DoubleMultiplierIdentity

class ModifiedIdentity5 extends TripleMultiplierIdentity with DoubleMultiplierIdentity {
  override def identity: Int = super[DoubleMultiplierIdentity].identity
}

class ModifiedIdentity6 extends TripleMultiplierIdentity with DoubleMultiplierIdentity {
  override def identity: Int = super[TripleMultiplierIdentity].identity
}

object ModifiedIdentityUser {
  def main(args: Array[String]): Unit = {
    val instance1 = new ModifiedIdentity1
    val instance2 = new ModifiedIdentity2
    val instance3 = new ModifiedIdentity3
    val instance4 = new ModifiedIdentity4
    val instance5 = new ModifiedIdentity5
    val instance6 = new ModifiedIdentity6

    println(s"Result 1: ${instance1.identity}")
    println(s"Result 2: ${instance2.identity}")
    println(s"Result 3: ${instance3.identity}")
    println(s"Result 4: ${instance4.identity}")
    println(s"Result 5: ${instance5.identity}")
    println(s"Result 6: ${instance6.identity}")
  }
}
