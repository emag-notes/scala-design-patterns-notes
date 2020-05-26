package sdp.ch07.structual.bridge

import org.apache.commons.codec.binary.Hex
import sdp.ch07.structual.bridge.common.Hasher

class Sha1Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
}

class Sha256Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
}

class Md5Hasher extends Hasher {
  override def hash(data: String): String = new String(Hex.encodeHex(getDigest("MD5", data).digest()))
}
