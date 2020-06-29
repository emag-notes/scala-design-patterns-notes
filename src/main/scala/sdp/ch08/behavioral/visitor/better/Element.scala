package sdp.ch08.behavioral.visitor.better

abstract class Element(text: String) {
  def accept(visitor: Element => Unit): Unit = visitor(this)
}

case class Title(text: String)                  extends Element(text)
case class Text(text: String)                   extends Element(text)
case class HyperLink(text: String, url: String) extends Element(text)

class Document(parts: List[Element]) {
  def accept(visitor: Element => Unit): Unit = parts.foreach(_.accept(visitor))
}
