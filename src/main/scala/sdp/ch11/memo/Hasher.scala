package sdp.ch11.memo

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import scalaz.Memo

class Hasher extends Memoizer {
  def md5(input: String): String = {
    println(s"Calling md5 for $input.")
    new String(Hex.encodeHex(MessageDigest.getInstance("MD5").digest(input.getBytes())))
  }

  val memoMd5: String => String = memo(md5)

  val memoMd5Scalaz: String => String = Memo.immutableHashMapMemo { md5 }
}
