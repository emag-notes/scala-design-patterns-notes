package sdp.ch08.behavioral.visitor

trait Visitor {
  def visit(title: Title): Unit
  def visit(text: Text): Unit
  def visit(hyperLink: HyperLink): Unit
}

class HtmlExporterVisitor extends Visitor {
  val line: String = System.getProperty("line.separator")
  val builder      = new StringBuilder

  def getHtml: String = builder.toString

  override def visit(title: Title): Unit =
    builder.append(s"<h1>${title.text}</h1>").append(line)

  override def visit(text: Text): Unit =
    builder.append(s"<p>${text.text}</p>").append(line)

  override def visit(hyperLink: HyperLink): Unit =
    builder.append(s"""<a href="${hyperLink.url}">${hyperLink.text}</a>""").append(line)
}

class PlainTextExporterVisitor extends Visitor {
  val line: String = System.getProperty("line.separator")
  val builder      = new StringBuilder

  def getText: String = builder.toString

  override def visit(title: Title): Unit =
    builder.append(title.text).append(line)

  override def visit(text: Text): Unit =
    builder.append(text.text).append(line)

  override def visit(hyperLink: HyperLink): Unit =
    builder.append(s"${hyperLink.text} (${hyperLink.url})").append(line)
}

object VisitorExample {
  def main(args: Array[String]): Unit = {
    val document = new Document(
      List(
        new Title("The Visitor Pattern Example"),
        new Text("The visitor pattern helps us add extra functionality without changing the classes."),
        new HyperLink("Go check it online!", "https://www.google.com"),
        new Text("Thanks!")
      )
    )

    val htmlExporter      = new HtmlExporterVisitor
    val plainTextExporter = new PlainTextExporterVisitor

    println(s"Export to html:")
    document.accept(htmlExporter)
    println(htmlExporter.getHtml)

    println(s"Export to plain:")
    document.accept(plainTextExporter)
    println(plainTextExporter.getText)
  }
}
