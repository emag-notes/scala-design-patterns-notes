package sdp.ch11.duck

object DuckTypingExample {
  def printSentenceParts(sentence: String, parser: { def parse(sentence: String): Array[String] }): Unit =
    parser.parse(sentence).foreach(println)

  def main(args: Array[String]): Unit = {
    val tokenizerParser = new SentenceParserTokenize
    val splitParser     = new SentenceParserSplit

    val sentence = "This is the sentence we will be splitting."

    println("Using the tokenize parser: ")
    printSentenceParts(sentence, tokenizerParser)

    println("Using the split parser: ")
    printSentenceParts(sentence, splitParser)
  }
}
