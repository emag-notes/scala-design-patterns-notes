package sdp.ch07.structual.bridge.scala

import org.apache.commons.codec.binary.Hex
import sdp.ch07.structual.bridge.common.Hasher

trait Sha1Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
}

trait Sha256Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
}

trait Md5Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("MD5", data).digest()))
}
