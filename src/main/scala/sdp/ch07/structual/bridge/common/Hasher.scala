package sdp.ch07.structual.bridge.common

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

trait Hasher {
  def hash(data: String): String

  protected def getDigest(algorithm: String, data: String): MessageDigest = {
    val crypt = MessageDigest.getInstance(algorithm)
    crypt.reset()
    crypt.update(data.getBytes(StandardCharsets.UTF_8))
    crypt
  }
}
