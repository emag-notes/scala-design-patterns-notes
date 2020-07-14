package sdp.ch12.real_life_applications.monads

import scalaz._
import Scalaz._
import effect._
import IO._

import sdp.ch12.real_life_applications.monads.model.Person

object IOMonadExample {
  def main(args: Array[String]): Unit = {
    args match {
      case Array(inputFile, isWriteToFile) =>
        val people = {
          for {
            line   <- readFile(inputFile)
            person <- Person.fromArray(line.split("\t"))
          } yield person
        }.pure[IO]

        println("Still haven't done any IO!")
        println("About to do some...")
        if (isWriteToFile.toBoolean) {
          val writePeople = for {
            _          <- putStrLn("Read people successfully. Where to write them down?")
            outputFile <- readLn
            p          <- people
            _          <- writeFile(outputFile, p.map(_.toString)).pure[IO]
          } yield ()
          println("Writing to file using toString.")
          writePeople.unsafePerformIO
        } else {
          println(s"Just got the following people: ${people.unsafePerformIO.toList}")
        }

      case _ =>
        System.err.println("Please provide input file and true/false whether to write to file.")
        sys.exit(-1)
    }
  }
}
