package sdp.ch11

import sdp.ch11.pimp.model.Person

package object pimp {
  implicit class StringExtensions(val s: String) extends AnyVal {
    def isAllUpperCase: Boolean =
      !(0 until s.length).exists { index => s.charAt(index).isLower }
  }

  implicit class PersonSeqExtensions(val seq: Iterable[Person]) extends AnyVal {
    def saveToDatabase(): Unit = {
      seq.foreach { person => println(s"Saved: $person to the database.") }
    }
  }
}
