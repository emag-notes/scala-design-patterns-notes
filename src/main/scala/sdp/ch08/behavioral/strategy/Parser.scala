package sdp.ch08.behavioral.strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.json4s.{DefaultFormats, StreamInput}
import org.json4s.jackson.JsonMethods
import sdp.ch08.behavioral.strategy.model.Person

trait Parser[T] {
  def parse(file: String): List[T]
}

class CSVParser extends Parser[Person] {
  override def parse(file: String): List[Person] =
    CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file))).all().map {
      case List(name, age, address) => Person(name, age.toInt, address)
    }
}

class JsonParser extends Parser[Person] {
  implicit val formats: DefaultFormats.type = DefaultFormats

  override def parse(file: String): List[Person] =
    JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file))).extract[List[Person]]
}

object Parser {
  def apply(filename: String): Parser[Person] =
    filename match {
      case f if f.endsWith(".json") => new JsonParser
      case f if f.endsWith(".csv")  => new CSVParser
      case f                        => throw new RuntimeException(s"Unknown format: $f")
    }
}

class PersonApplication[T](parser: Parser[T]) {
  def write(file: String): Unit =
    println(s"Got the following data ${parser.parse(file)}")
}

object ParserExample {
  def main(args: Array[String]): Unit = {
    val csvPeople  = Parser("/people.csv")
    val jsonPeople = Parser("/people.json")

    val applicationCsv  = new PersonApplication(csvPeople)
    val applicationJson = new PersonApplication(jsonPeople)

    println("Using the csv:")
    applicationCsv.write("/people.csv")

    println("Using the json:")
    applicationJson.write("/people.json")
  }
}
