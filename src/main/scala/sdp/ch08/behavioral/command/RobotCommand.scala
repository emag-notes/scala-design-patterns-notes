package sdp.ch08.behavioral.command

import scala.collection.mutable.ListBuffer

trait RobotCommand {
  def execute(): Unit
}

final case class MakeSandwichCommand(robot: Robot) extends RobotCommand {
  override def execute(): Unit = robot.makeSandwich()
}

final case class PourJuiceCommand(robot: Robot) extends RobotCommand {
  override def execute(): Unit = robot.pourJuice()
}

final case class CleanUPCommand(robot: Robot) extends RobotCommand {
  override def execute(): Unit = robot.cleanUp()
}

class RobotController {
  val history = ListBuffer[RobotCommand]()

  def issueCommand(command: RobotCommand): Unit = {
    command +=: history
    command.execute()
  }

  def showHistory(): Unit = {
    history.foreach(println)
  }
}

class RobotByNameController {
  val history = ListBuffer[() => Unit]()

  def issueCommand(command: => Unit): Unit = {
    command _ +=: history
    command
  }

  def showHistory(): Unit = {
    history.foreach(println)
  }
}

object RobotExample {
  def main(args: Array[String]): Unit = {
    val robot           = new Robot
    val robotController = new RobotController

    robotController.issueCommand(MakeSandwichCommand(robot))
    println("I'm eating and having some juice.")
    robotController.issueCommand(CleanUPCommand(robot))

    println("Here is what I asked my robot to do:")
    robotController.showHistory()
  }
}

object RobotByNameExample {
  def main(args: Array[String]): Unit = {
    val robot           = new Robot
    val robotController = new RobotByNameController

    robotController.issueCommand(MakeSandwichCommand(robot).execute())
    robotController.issueCommand(PourJuiceCommand(robot).execute())
    println("I'm eating and having some juice.")
    robotController.issueCommand(CleanUPCommand(robot).execute())

    println("Here is what I asked my robot to do:")
    robotController.showHistory()
  }
}
