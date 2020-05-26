package sdp.ch07.structual.bridge.scala

import sdp.ch07.structual.bridge.common.Hasher

abstract class PasswordConverterBase {
  self: Hasher =>

  def convert(password: String): String
}

class SimplePasswordConverterScala extends PasswordConverterBase {
  self: Hasher =>

  override def convert(password: String): String = hash(password)
}

class SaltedPasswordConverterScala(salt: String) extends PasswordConverterBase {
  self: Hasher =>

  override def convert(password: String): String = hash(s"$salt:$password")
}

object ScalaBridgeExample {
  def main(args: Array[String]): Unit = {
    val p1 = new SimplePasswordConverterScala with Sha256Hasher
    val p2 = new SimplePasswordConverterScala with Sha1Hasher
    val p3 = new SimplePasswordConverterScala with Md5Hasher

    println(s"'password' in SHA-256 is:\t${p1.convert("password")}")
    println(s"'password' in SHA-1 is:\t\t${p2.convert("password")}")
    println(s"'password' in MD5 is:\t\t${p3.convert("password")}")

    val p4 = new SaltedPasswordConverterScala("8jsdf32T^$%") with Sha256Hasher
    val p5 = new SaltedPasswordConverterScala("8jsdf32T^$%") with Sha1Hasher
    val p6 = new SaltedPasswordConverterScala("8jsdf32T^$%") with Md5Hasher

    println(s"'password' in salted SHA-256 is:\t${p4.convert("password")}")
    println(s"'password' in salted SHA-1 is:\t\t${p5.convert("password")}")
    println(s"'password' in salted MD5 is:\t\t${p6.convert("password")}")
  }
}
