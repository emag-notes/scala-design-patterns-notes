package sdp.ch07.structual.proxy

import java.io.{BufferedReader, IOException, InputStreamReader}

import scala.jdk.CollectionConverters._
import scala.util.Using

trait FileReader {
  def readFileContents(): String
}

class FileReaderReal(filename: String) extends FileReader {
  val contents: String = {
    Using
      .Manager { use =>
        val stream = use(this.getClass.getResourceAsStream(filename))
        val reader = use(new BufferedReader(new InputStreamReader(stream)))
        reader.lines().iterator().asScala.mkString(System.getProperty("line.separator"))
      }
      .fold(
        err => throw new IOException(err),
        suc => suc
      )
  }

  println(s"Finished reading the actual file: $filename")

  override def readFileContents(): String = contents
}

class FileReaderProxy(filename: String) extends FileReader {
  private lazy val fileReader: FileReaderReal = new FileReaderReal(filename)

  override def readFileContents(): String = fileReader.readFileContents()
}

object ProxyExample {
  def main(args: Array[String]): Unit = {
    val fileMap = Map(
      "file1.txt" -> new FileReaderProxy("/file1.txt"),
      "file2.txt" -> new FileReaderProxy("/file2.txt"),
      "file3.txt" -> new FileReaderProxy("/file3.txt"),
      "file4.txt" -> new FileReaderReal("/file1.txt")
    )
    println("Created the map. You should have seen file1.txt read because it wasn't used in a proxy.")
    println(s"Reading file1.txt from the proxy: ${fileMap("file1.txt").readFileContents()}")
    println(s"Reading file3.txt from the proxy: ${fileMap("file3.txt").readFileContents()}")
  }
}
