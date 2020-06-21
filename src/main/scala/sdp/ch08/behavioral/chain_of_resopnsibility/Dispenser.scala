package sdp.ch08.behavioral.chain_of_resopnsibility

trait Dispenser {
  val amount: Int
  val next: Option[Dispenser]

  def dispense(money: Money): Unit = {
    if (money.amount >= amount) {
      val notes = money.amount / amount
      val left  = money.amount % amount
      println(s"Dispensing $notes note/s of $amount.")
      if (left > 0) next.map(_.dispense(Money(left)))
    } else {
      next.foreach(_.dispense(money))
    }
  }
}

class Dispenser50(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 50
}

class Dispenser20(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 20
}

class Dispenser10(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 10
}

class Dispenser5(val next: Option[Dispenser]) extends Dispenser {
  override val amount: Int = 5
}

trait PartialFunctionDispenser {
  def dispense(dispenserAmount: Int): PartialFunction[Money, Money] = {
    case Money(amount) if amount >= dispenserAmount =>
      val notes = amount / dispenserAmount
      val left  = amount % dispenserAmount
      println(s"Dispensing $notes note/s of $dispenserAmount.")
      Money(left)
    case m @ Money(_) =>
      m
  }
}
