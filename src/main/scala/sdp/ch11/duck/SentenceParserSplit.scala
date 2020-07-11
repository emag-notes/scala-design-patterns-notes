package sdp.ch11.duck

class SentenceParserSplit {
  def parse(sentence: String): Array[String] =
    sentence.split("\\s")
}
