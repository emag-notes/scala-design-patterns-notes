package sdp.ch07.structual.decorator

import java.io._
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging
import sdp.ch07.structual.decorator.common.{AdvancedInputReader, InputReader}

import scala.util.Using

abstract class InputReaderDecorator(inputReader: InputReader) extends InputReader {
  override def readLines(): LazyList[String] = inputReader.readLines()
}

class CapitalizedInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
  override def readLines(): LazyList[String] = super.readLines().map(_.toUpperCase)
}

class CompressingInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) with LazyLogging {
  override def readLines(): LazyList[String] = super.readLines().map { line =>
    val text = line.getBytes(StandardCharsets.UTF_8)
    logger.info("Length before compression: {}", text.length)

    Using
      .Manager { use =>
        val output     = use(new ByteArrayOutputStream())
        val compressor = use(new GZIPOutputStream(output))
        compressor.write(text, 0, text.length)
        val outputByteArray = output.toByteArray
        logger.info("Length after compression: {}", outputByteArray.length)
        new String(outputByteArray, StandardCharsets.UTF_8)
      }
      .fold(
        err => throw new IOException(err),
        suc => suc
      )
  }
}

class Base64EncoderInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
  override def readLines(): LazyList[String] =
    super.readLines().map { line => Base64.getEncoder.encodeToString(line.getBytes(StandardCharsets.UTF_8)) }
}

object DecoratorExample {
  def main(args: Array[String]): Unit = {
    Using(
      new BufferedReader(
        new InputStreamReader(
          new BufferedInputStream(this.getClass.getResourceAsStream("/data.txt"))
        )
      )
    )(stream => {
      val reader = new CapitalizedInputReader(new AdvancedInputReader(stream))
      reader.readLines().foreach(println)
    })
  }
}

object DecoratorExampleBig {
  def main(args: Array[String]): Unit = {
    Using(
      new BufferedReader(
        new InputStreamReader(
          new BufferedInputStream(this.getClass.getResourceAsStream("/data.txt"))
        )
      )
    )(stream => {
      val reader =
        // format: off
        new CompressingInputReader(
          new Base64EncoderInputReader(
            new CapitalizedInputReader(
              new AdvancedInputReader(stream)))
        )
        // format: on

      reader.readLines().foreach(println)
    })
  }
}
