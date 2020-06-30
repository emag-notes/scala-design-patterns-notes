package sdp.ch10.monoids

object MonoidFolding {
  def main(args: Array[String]): Unit = {
    val strings = List("This is\n", "a list of\n", "strings!")
    val numbers = List(1, 2, 3, 4, 5, 6)

    val stringsLeftFolded = strings.foldLeft(stringConcatenation.zero)(stringConcatenation.op)
    println(s"Left folded:\n$stringsLeftFolded")

    val stringRightFolded = strings.foldRight(stringConcatenation.zero)(stringConcatenation.op)
    println(s"Right folded:\n$stringRightFolded")

    val numbersLeftFolded = numbers.foldLeft(intMultiplication.zero)(intMultiplication.op)
    println(s"6! is: $numbersLeftFolded")
  }
}

object MonoidFoldingGeneric {
  def main(args: Array[String]): Unit = {
    val strings = List("This is\n", "a list of\n", "strings!")
    val numbers = List(1, 2, 3, 4, 5, 6)

    val stringsLeftFolded = MonoidOperations.fold(strings, stringConcatenation)
    println(s"Left folded:\n$stringsLeftFolded")

    val stringRightFolded = MonoidOperations.fold(strings, stringConcatenation)
    println(s"Right folded:\n$stringRightFolded")

    val numbersLeftFolded = MonoidOperations.fold(numbers, intMultiplication)
    println(s"6! is: $numbersLeftFolded")
  }
}

object MonoidBalancedFold {
  def main(args: Array[String]): Unit = {
    val numbers = Array(1, 2, 3, 4)
    println(s"4! is: ${MonoidOperations.balancedFold(numbers.toIndexedSeq, intMultiplication)(identity)}")
  }
}

object MonoidFoldingGenericPar {
  def main(args: Array[String]): Unit = {
    val strings = List("This is\n", "a list of\n", "strings!")
    val numbers = List(1, 2, 3, 4, 5, 6)

    val stringsLeftFolded = MonoidOperations.foldPar(strings, stringConcatenation)
    println(s"Left folded:\n$stringsLeftFolded")

    val stringRightFolded = MonoidOperations.foldPar(strings, stringConcatenation)
    println(s"Right folded:\n$stringRightFolded")

    val numbersLeftFolded = MonoidOperations.foldPar(numbers, intMultiplication)
    println(s"6! is: $numbersLeftFolded")
  }
}

object ComposedMonoid {
  def main(args: Array[String]): Unit = {
    val numbers       = Array(1, 2, 3, 4, 5, 6)
    val sumAndProduct = compose(intAddition, intMultiplication)

    println(
      s"The sum and product is ${MonoidOperations.balancedFold(numbers.toIndexedSeq, sumAndProduct)(i => (i, i))}"
    )
  }
}

object FeatureCounting {
  def main(args: Array[String]): Unit = {
    val features                                = Array("hello", "features", "for", "ml", "hello", "for", "features")
    val counterMonoid: Monoid[Map[String, Int]] = mapMerge(intAddition)

    println(
      s"The features are: ${MonoidOperations.balancedFold(features.toIndexedSeq, counterMonoid)(i => Map(i -> 1))}"
    )
  }
}

object FeatureCountingOneOff {
  def main(args: Array[String]): Unit = {
    val features = Array("hello", "features", "for", "ml", "hello", "for", "features")

    println(s"The features are: ${features.foldLeft(Map[String, Int]()) {
      case (res, feature) =>
        res.updated(feature, res.getOrElse(feature, 0) + 1)
    }}")

  }
}
