package sdp.ch11.implicits.di

object ImplicitDIExample {
  def main(args: Array[String]): Unit = {
    println(s"The average age of the people is: ${userService.getAverageAgeOrPeople}")
  }
}
