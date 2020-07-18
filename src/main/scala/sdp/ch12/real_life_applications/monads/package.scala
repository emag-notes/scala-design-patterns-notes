package sdp.ch12.real_life_applications

import java.io.{File, IOException, PrintWriter}

import scala.io.Source
import scala.util.Using

package object monads {
  def readFile(path: String): Iterator[String] = {
    println(s"Reading file $path")
    Source.fromFile(path).getLines
  }

  def writeFile(path: String, lines: Iterator[String]): Unit = {
    println(s"Writing file $path")
    printToFile(new File(path)) { p => lines.foreach(p.println) }
  }

  private def printToFile(file: File)(writeOp: PrintWriter => Unit): Unit = {
    Using(new PrintWriter(file)) { writer => writeOp(writer) }.fold(
      err => throw new IOException(err),
      _ => ()
    )
  }
}
