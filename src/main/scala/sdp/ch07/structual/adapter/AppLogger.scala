package sdp.ch07.structual.adapter

// we cannot change the class
class Logger {
  def log(message: String, severity: String): Unit = {
    println(s"${severity.toUpperCase}: $message")
  }
}

trait Log {
  def debug(message: String): Unit
  def info(message: String): Unit
  def warning(message: String): Unit
  def error(message: String): Unit
}

class AppLogger extends Logger with Log {
  override def debug(message: String): Unit   = log(message, "debug")
  override def info(message: String): Unit    = log(message, "info")
  override def warning(message: String): Unit = log(message, "warning")
  override def error(message: String): Unit   = log(message, "error")
}

object AppLoggerExample {
  def main(args: Array[String]): Unit = {
    val logger = new AppLogger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}

// final
final class FinalLogger {
  def log(message: String, severity: String): Unit = {
    println(s"${severity.toUpperCase}: $message")
  }
}

class FinalAppLogger extends Log {
  private val logger = new FinalLogger

  override def debug(message: String): Unit   = logger.log(message, "debug")
  override def info(message: String): Unit    = logger.log(message, "info")
  override def warning(message: String): Unit = logger.log(message, "warning")
  override def error(message: String): Unit   = logger.log(message, "error")
}

object FinalAppLoggerExample {
  def main(args: Array[String]): Unit = {
    val logger = new FinalAppLogger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}

object FinalAppLoggerImplicitExample {
  def main(args: Array[String]): Unit = {
    val logger: Log = new FinalLogger
    logger.debug(s"logger.getClass: ${logger.getClass}")
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}
