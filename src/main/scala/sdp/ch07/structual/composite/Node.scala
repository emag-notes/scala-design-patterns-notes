package sdp.ch07.structual.composite

import scala.collection.mutable.ListBuffer

trait Node {
  def print(prefix: String): Unit
}

class Leaf(data: String) extends Node {
  override def print(prefix: String): Unit = println(s"$prefix$data")
}

class Tree extends Node {
  private val children = ListBuffer.empty[Node]

  override def print(prefix: String): Unit = {
    println(s"$prefix(")
    children.foreach(_.print(s"$prefix$prefix"))
    println(s"$prefix)")
  }

  def add(child: Node): Unit = {
    children += child
  }

  def remove(): Unit = {
    if (children.nonEmpty) {
      children.remove(0)
    }
  }
}

object CompositeExample {
  def main(args: Array[String]): Unit = {
    val tree = new Tree

    tree.add(new Leaf("leaf 1"))

    val subTree1 = new Tree
    subTree1.add(new Leaf("leaf 2"))

    val subTree2 = new Tree
    subTree2.add(new Leaf("leaf 3"))
    subTree2.add(new Leaf("leaf 4"))
    subTree1.add(subTree2)

    tree.add(subTree1)

    val subTree3 = new Tree
    val subTree4 = new Tree
    subTree4.add(new Leaf("leaf 5"))
    subTree4.add(new Leaf("leaf 6"))
    subTree3.add(subTree4)

    tree.add(subTree3)

    tree.print("-")
  }
}
