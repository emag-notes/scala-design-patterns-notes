package sdp.ch05.components

import sdp.ch05.components.model.Food

class Robot extends RobotRegistry {
  def cook(what: String): Food = cooker.cook(what)
  def getTime: String          = time.getTime
}

object RobotExample {
  def main(args: Array[String]): Unit = {
    val robot = new Robot
    println(robot.getTime)
    println(robot.cook("chips"))
    println(robot.cook("sandwich"))
  }
}
