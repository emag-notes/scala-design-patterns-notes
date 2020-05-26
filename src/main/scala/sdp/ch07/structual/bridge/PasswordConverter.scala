package sdp.ch07.structual.bridge

import sdp.ch07.structual.bridge.common.Hasher

abstract class PasswordConverter(hasher: Hasher) {
  def convert(password: String): String
}

class SimplePasswordConverter(hasher: Hasher) extends PasswordConverter(hasher) {
  override def convert(password: String): String = hasher.hash(password)
}

class SaltedPasswordConverter(salt: String, hasher: Hasher) extends PasswordConverter(hasher) {
  override def convert(password: String): String = hasher.hash(s"$salt:$password")
}

object BridgeExample {
  def main(args: Array[String]): Unit = {
    val p1 = new SimplePasswordConverter(new Sha256Hasher)
    val p2 = new SimplePasswordConverter(new Sha1Hasher)
    val p3 = new SimplePasswordConverter(new Md5Hasher)

    println(s"'password' in SHA-256 is:\t${p1.convert("password")}")
    println(s"'password' in SHA-1 is:\t\t${p2.convert("password")}")
    println(s"'password' in MD5 is:\t\t${p3.convert("password")}")

    val p4 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha256Hasher)
    val p5 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha1Hasher)
    val p6 = new SaltedPasswordConverter("8jsdf32T^$%", new Md5Hasher)

    println(s"'password' in salted SHA-256 is:\t${p4.convert("password")}")
    println(s"'password' in salted SHA-1 is:\t\t${p5.convert("password")}")
    println(s"'password' in salted MD5 is:\t\t${p6.convert("password")}")
  }
}
