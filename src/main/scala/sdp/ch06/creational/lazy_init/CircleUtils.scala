package sdp.ch06.creational.lazy_init

import java.util.Properties

object CircleUtils {
  private val basicPi = 3.14d

  private lazy val precisePi: Double = {
    println("Reading properties for the precise PI.")
    val props = new Properties()
    props.load(getClass.getResourceAsStream("/pi.properties"))
    props.getProperty("pi.high").toDouble
  }

  def area(radius: Double, isPrecise: Boolean = false): Double = {
    val pi: Double = if (isPrecise) precisePi else basicPi
    pi * Math.pow(radius, 2)
  }
}

object Example {
  def main(args: Array[String]): Unit = {
    println(s"The basic area for a circle with radius 2.5 is ${CircleUtils.area(2.5)}")
    println(s"The precise area for a circle with radius 2.5 is ${CircleUtils.area(2.5, isPrecise = true)}")
    println(s"The basic area for a circle with radius 6.78 is ${CircleUtils.area(6.78)}")
    println(s"The precise area for a circle with radius 6.78 is ${CircleUtils.area(6.78, isPrecise = true)}")
  }
}
