package sdp.ch07.structual.decorator

import java.io._
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging
import sdp.ch07.structual.decorator.common.{AdvancedInputReader, InputReader}

import scala.util.Using

trait CapitalizedInputReaderTrait extends InputReader {
  abstract override def readLines(): LazyList[String] = super.readLines().map(_.toUpperCase())
}

trait CompressingInputReaderTrait extends InputReader with LazyLogging {
  abstract override def readLines(): LazyList[String] = super.readLines().map { line =>
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

trait Base64EncoderInputReaderTrait extends InputReader {
  abstract override def readLines(): LazyList[String] =
    super.readLines().map { line => Base64.getEncoder.encodeToString(line.getBytes(StandardCharsets.UTF_8)) }
}

object StackableTraitBigExample {
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
        new AdvancedInputReader(stream)
          with CapitalizedInputReaderTrait
          with Base64EncoderInputReaderTrait
          with CompressingInputReaderTrait
        // format: on

      reader.readLines().foreach(println)
    })
  }
}
