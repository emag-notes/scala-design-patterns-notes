package sdp.ch07.structual.facade

import sdp.ch07.structual.facade.model.Person

class DataReader extends DataDownloader with DataDecoder with DataDeserializer {
  def readPerson(url: String): Person = {
    val data = download(url)
    val json = decode(data)
    parse[Person](json)
  }
}

object FacadeExample {
  def main(args: Array[String]): Unit = {
    val reader = new DataReader
    val person = reader.readPerson("https://www.ivan-nikolov.com/")
    println(s"We just read the following person: $person")
  }
}
