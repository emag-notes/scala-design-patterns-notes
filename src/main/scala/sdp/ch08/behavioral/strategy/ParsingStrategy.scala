package sdp.ch08.behavioral.strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.json4s.{DefaultFormats, StreamInput}
import org.json4s.jackson.JsonMethods
import sdp.ch08.behavioral.strategy.model.Person

class Application[T](strategy: String => List[T]) {
  def write(file: String): Unit = {
    println(s"Got the following data ${strategy(file)}")
  }
}

object StrategyFactory {
  implicit val formats: DefaultFormats.type = DefaultFormats

  def apply(filename: String): String => List[Person] =
    filename match {
      case f if f.endsWith(".json") => parseJson
      case f if f.endsWith(".csv")  => parseCsv
      case f                        => throw new RuntimeException(s"Unknown format: $f")
    }

  def parseJson(file: String): List[Person] =
    JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file))).extract[List[Person]]

  def parseCsv(file: String): List[Person] =
    CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file))).all().map {
      case List(name, age, address) => Person(name, age.toInt, address)
    }
}

object StrategyExample {
  def main(args: Array[String]): Unit = {
    val applicationCsv  = new Application(StrategyFactory("people.csv"))
    val applicationJson = new Application(StrategyFactory("people.json"))

    println("Using the csv:")
    applicationCsv.write("/people.csv")

    println("Using the json:")
    applicationJson.write("/people.json")
  }
}
