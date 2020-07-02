package sdp.ch10.monads

import java.io.{File, IOException, PrintWriter}

import scala.io.Source
import scala.util.Using

package object io {
  def readFile(path: String): IOAction[Iterator[String]] =
    IOAction(Source.fromFile(path).getLines())

  def writeFile(path: String, lines: Iterator[String]): IOAction[Unit] =
    IOAction {
      val file = new File(path)
      printToFile(file) { p => lines.foreach(p.println) }
    }

  private def printToFile(file: File)(writeOp: PrintWriter => Unit): Unit = {
    Using(new PrintWriter(file)) { writer => writeOp(writer) }.fold(
      err => throw new IOException(err),
      _ => ()
    )
  }
}
