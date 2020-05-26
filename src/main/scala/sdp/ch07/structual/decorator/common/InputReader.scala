package sdp.ch07.structual.decorator.common

import java.io.BufferedReader
import scala.jdk.CollectionConverters._

trait InputReader {
  def readLines(): LazyList[String]
}

class AdvancedInputReader(reader: BufferedReader) extends InputReader {
  override def readLines(): LazyList[String] = reader.lines().iterator().asScala.to(LazyList)
}
