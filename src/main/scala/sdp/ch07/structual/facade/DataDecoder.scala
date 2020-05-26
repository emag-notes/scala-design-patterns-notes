package sdp.ch07.structual.facade

import java.nio.charset.StandardCharsets
import java.util.Base64

trait DataDecoder {
  def decode(data: Array[Byte]): String =
    new String(Base64.getDecoder.decode(data), StandardCharsets.UTF_8)
}
