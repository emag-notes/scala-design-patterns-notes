package sdp.ch07.structual.facade

import java.util.concurrent.TimeUnit

import com.typesafe.scalalogging.LazyLogging

trait DataDownloader extends LazyLogging {
  def download(url: String): Array[Byte] = {
    logger.info("Downloading from: {}", url)
    TimeUnit.SECONDS.sleep(5)
    //    {
    //      "name": "Ivan",
    //      "age": 26
    //    }
    //    the string below is the Base64 encoded Json above.
    "ew0KICAgICJuYW1lIjogIkl2YW4iLA0KICAgICJhZ2UiOiAyNg0KfQ==".getBytes
  }
}
