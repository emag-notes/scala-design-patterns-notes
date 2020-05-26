package sdp.ch07.structual.facade

import org.json4s.{DefaultFormats, StringInput}
import org.json4s.jackson.JsonMethods

trait DataDeserializer {
  implicit val formats: DefaultFormats.type = DefaultFormats

  def parse[T](data: String)(implicit m: Manifest[T]): T =
    JsonMethods.parse(StringInput(data)).extract[T]
}
