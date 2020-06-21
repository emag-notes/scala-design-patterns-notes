package sdp.ch08.behavioral.chain_of_resopnsibility

import scala.io.Source

class ATM {
  val dispenser: Dispenser = {
    val d1 = new Dispenser5(None)
    val d2 = new Dispenser10(Some(d1))
    val d3 = new Dispenser20(Some(d2))
    new Dispenser50(Some(d3))
  }

  def requestMoney(money: Money): Unit = {
    if (money.amount % 5 != 0) {
      println("The smallest nominal is 5 and we cannot satisfy your request.")
    } else {
      dispenser.dispense(money)
    }
  }
}

class PartialFunctionATM extends PartialFunctionDispenser {
  val dispenser: PartialFunction[Money, Money] =
    dispense(50)
      .andThen(dispense(20))
      .andThen(dispense(10))
      .andThen(dispense(5))

  def requestMoney(money: Money): Unit = {
    if (money.amount % 5 != 0) {
      println("The smallest nominal is 5 and we cannot satisfy your request.")
    } else {
      dispenser(money)
    }
  }
}

object ATMExample {
  def main(args: Array[String]): Unit = {
    val atm = new ATM
    printHelp()

    Source.stdin.getLines().foreach { line => processLine(line, atm) }
  }

  def printHelp(): Unit = {
    println("Usage:")
    println("1. Write an amount to withdraw...")
    println("2. Write EXIT to quit the application.")
  }

  def processLine(line: String, atm: ATM): Unit = {
    line match {
      case "EXIT" =>
        println("Bye!")
        System.exit(0)
      case l =>
        try {
          atm.requestMoney(Money(l.toInt))
          println(s"Thanks!")
        } catch {
          case _: Throwable =>
            println(s"Invalid input: $l.")
            printHelp()
        }
    }
  }
}

object PartialFunctionATMExample {
  def main(args: Array[String]): Unit = {
    val atm = new PartialFunctionATM
    printHelp()

    Source.stdin.getLines().foreach { line => processLine(line, atm) }
  }

  def printHelp(): Unit = {
    println("Usage:")
    println("1. Write an amount to withdraw...")
    println("2. Write EXIT to quit the application.")
  }

  def processLine(line: String, atm: PartialFunctionATM): Unit = {
    line match {
      case "EXIT" =>
        println("Bye!")
        System.exit(0)
      case l =>
        try {
          atm.requestMoney(Money(l.toInt))
          println(s"Thanks!")
        } catch {
          case _: Throwable =>
            println(s"Invalid input: $l.")
            printHelp()
        }
    }
  }
}
