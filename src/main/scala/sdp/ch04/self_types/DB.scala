package sdp.ch04.self_types

trait DB {
  def connect(): Unit      = println("Connected")
  def dropDatabase(): Unit = println("Dropping!")
  def close(): Unit        = println("Closed.")
}

trait UserDB {
  this: DB =>

  def createUser(username: String): Unit = {
    connect()
    try {
      println(s"Creating a user: $username")
    } finally {
      close()
    }
  }

  def getUser(username: String): Unit = {
    connect()
    try {
      println(s"Getting a user: $username")
    } finally {
      close()
    }
  }
}

trait UserService {
  this: UserDB =>

  // does not compile
//  def bad(): Unit = dropDatabase()
}

//trait UserDB extends DB {
//  def createUser(username: String): Unit = {
//    connect()
//    try {
//      println(s"Creating a user: $username")
//    } finally {
//      close()
//    }
//  }
//
//  def getUser(username: String): Unit = {
//    connect()
//    try {
//      println(s"Getting a user: $username")
//    } finally {
//      close()
//    }
//  }
//}
//
//trait UserService extends UserDB {
//  def bad(): Unit = dropDatabase()
//}
