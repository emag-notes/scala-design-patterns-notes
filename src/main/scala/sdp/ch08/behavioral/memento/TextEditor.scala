package sdp.ch08.behavioral.memento

class TextEditor extends Originator[String] {
  private var builder: StringBuilder = new StringBuilder

  def append(text: String): Unit = builder.append(text)

  def delete(): Unit = if (builder.nonEmpty) {
    builder.deleteCharAt(builder.length - 1)
  }

  def text: String = builder.toString

  override def createMemento: Memento[String] =
    new TextEditorMemento(builder.toString)

  override def restore(memento: Memento[String]): Unit =
    this.builder = new StringBuilder(memento.getState)

  private class TextEditorMemento(val state: String) extends Memento[String]
}

class TextEditorManipulator extends Caretaker[String] {
  private val textEditor = new TextEditor

  def save(): Unit = status.push(textEditor.createMemento)

  def undo(): Unit = if (status.nonEmpty) {
    textEditor.restore(status.pop())
  }

  def append(text: String): Unit = {
    save()
    textEditor.append(text)
  }

  def delete(): Unit = {
    save()
    textEditor.delete()
  }

  def readText: String = textEditor.text
}

object TextEditorExample {
  def main(args: Array[String]): Unit = {
    val textEditorManipulator = new TextEditorManipulator
    textEditorManipulator.append("This is a chapter about memento.")
    println(s"The text is: '${textEditorManipulator.readText}'")
    // delete 2 characters
    println("Deleting 2 characters...")
    textEditorManipulator.delete()
    textEditorManipulator.delete()
    // see the text
    println(s"The text is: '${textEditorManipulator.readText}'")
    // undo
    println("Undoing...")
    textEditorManipulator.undo()
    println(s"The text is: '${textEditorManipulator.readText}'")
    // undo again
    println("Undoing...")
    textEditorManipulator.undo()
    println(s"The text is: '${textEditorManipulator.readText}'")
  }
}
