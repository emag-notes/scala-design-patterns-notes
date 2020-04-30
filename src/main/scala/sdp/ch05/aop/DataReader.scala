package sdp.ch05.aop

import org.json4s._
import org.json4s.jackson.JsonMethods._
import sdp.ch05.aop.model.Person

trait DataReader {
  def readData(): List[Person]
  def readDataInefficiently(): List[Person]
}

class DataReaderImpl extends DataReader {
  implicit val formats: DefaultFormats.type = DefaultFormats

  private def readUntimed(): List[Person] =
    parse(StreamInput(getClass.getResourceAsStream("/users.json"))).extract[List[Person]]

  override def readData(): List[Person] = readUntimed()

  override def readDataInefficiently(): List[Person] = {
    (1 to 10000).foreach(_ => readUntimed())
    readUntimed()
  }
}

object DataReaderExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImpl
    println(s"I just read the following data efficiently: ${dataReader.readData()}")
    println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}

object DataReaderAOPExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImpl with LoggingDataReader
    println(s"I just read the following data efficiently: ${dataReader.readData()}")
    println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}
