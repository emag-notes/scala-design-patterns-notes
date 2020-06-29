package sdp.ch08.behavioral.template

import java.io.{ByteArrayInputStream, IOException, InputStreamReader}
import java.nio.charset.StandardCharsets

import com.github.tototoshi.csv.CSVReader
import org.json4s.{DefaultFormats, StringInput}
import org.json4s.jackson.JsonMethods
import sdp.ch08.behavioral.template.model.Person

import scala.util.Using

abstract class DataFinder[T, Y] {
  def find(f: T => Option[Y]): Option[Y] =
    try {
      val data   = readData()
      val parsed = parse(data)
      f(parsed)
    } finally {
      cleanup()
    }

  def readData(): Array[Byte]
  def parse(data: Array[Byte]): T
  def cleanup(): Unit

  protected def fromClasspath(filenameOnClasspath: String): Array[Byte] =
    Using(this.getClass.getResourceAsStream(filenameOnClasspath)) { stream =>
      LazyList.continually(stream.read).takeWhile(_ != -1).map(_.toByte).toArray
    }.fold(
      err => throw new IOException(err),
      suc => suc
    )
}

class JsonDataFinder extends DataFinder[List[Person], Person] {
  implicit val formats: DefaultFormats.type = DefaultFormats

  override def readData(): Array[Byte] = fromClasspath("/people.json")

  override def parse(data: Array[Byte]): List[Person] =
    JsonMethods.parse(StringInput(new String(data, StandardCharsets.UTF_8))).extract[List[Person]]

  override def cleanup(): Unit = println("Reading json: nothing to do.")
}

class CSVDataFinder extends DataFinder[List[Person], Person] {
  override def readData(): Array[Byte] = fromClasspath("/people.csv")

  override def parse(data: Array[Byte]): List[Person] =
    CSVReader.open(new InputStreamReader(new ByteArrayInputStream(data))).all().map {
      case List(name, age, address) => Person(name, age.toInt, address)
    }

  override def cleanup(): Unit = println("Reading csv: nothing to do.")
}

object DataFinderExample {
  def main(args: Array[String]): Unit = {
    val jsonDataFinder = new JsonDataFinder
    val csvDataFinder  = new CSVDataFinder

    println(s"Find a person with name Ivan in the json: ${jsonDataFinder.find(_.find(_.name == "Ivan"))}")
    println(s"Find a person with name James in the json: ${jsonDataFinder.find(_.find(_.name == "James"))}")

    println(s"Find a person with name Maria in the json: ${csvDataFinder.find(_.find(_.name == "Maria"))}")
    println(s"Find a person with name Alice in the json: ${csvDataFinder.find(_.find(_.name == "Alice"))}")
  }
}
