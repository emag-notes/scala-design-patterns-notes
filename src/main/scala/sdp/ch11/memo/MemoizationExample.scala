package sdp.ch11.memo

object MemoizationExample {
  def main(args: Array[String]): Unit = {
    val hasher = new Hasher

    println("--- Memoizer")
    println(s"MD5 for 'hello' is '${hasher.memoMd5("hello")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5("bye")}'.")
    println(s"MD5 for 'hello' is '${hasher.memoMd5("hello")}'.")
    println(s"MD5 for 'bye1' is '${hasher.memoMd5("bye1")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5("bye")}'.")

    println("--- Scalaz Memoizer")
    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye1' is '${hasher.memoMd5Scalaz("bye1")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
  }
}
