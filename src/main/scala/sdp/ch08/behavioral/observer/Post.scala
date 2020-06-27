package sdp.ch08.behavioral.observer

import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

final case class Post(user: User, text: String) extends Observable[Post] {
  val comments: ListBuffer[Comment] = ListBuffer[Comment]()

  def addComment(comment: Comment): Unit = {
    comments.prepend(comment)
    notifyObservers()
  }
}

final case class Comment(user: User, text: String)

final case class User(name: String) extends Observer[Post] {
  override def handleUpdate(subject: Post): Unit =
    println(s"Hey, I'm $name. The post got some new comments: ${subject.comments}")
}

object PostExample extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val userIvan  = User("Ivan")
    val userMaria = User("Maria")
    val userJohn  = User("John")

    logger.info("Create a post")
    val post = Post(userIvan, "This is a post about the observer design pattern")
    logger.info("Add a comment")
    post.addComment(Comment(userIvan, "I hope you about the observer design pattern"))

    logger.info("John and Maria subscribe to the comments.")
    post.addObserver(userJohn)
    post.addObserver(userMaria)

    logger.info("Add a comment")
    post.addComment(Comment(userIvan, "Why are you so quiet? Do you like it?"))
    logger.info("Add a comment")
    post.addComment(Comment(userMaria, "It is amazing! Thanks!"))
  }
}
