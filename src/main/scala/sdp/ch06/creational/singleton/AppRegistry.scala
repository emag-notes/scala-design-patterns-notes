package sdp.ch06.creational.singleton

import java.util.concurrent.TimeUnit

import scala.collection.concurrent.{Map, TrieMap}

object AppRegistry {
  println("Registry initialization block called.")

  private val users: Map[String, String] = TrieMap.empty

  def addUser(id: String, name: String): Unit = users.put(id, name)
  def removeUser(id: String): Unit            = users.remove(id)
  def isUserRegistered(id: String): Boolean   = users.contains(id)
  def getAllUserNames: List[String]           = users.values.toList
}

object AppRegistryExample {
  def main(args: Array[String]): Unit = {
    println("Sleeping for 5 seconds.")
    TimeUnit.SECONDS.sleep(5)
    println("I woke up.")

    AppRegistry.addUser("1", "Ivan")
    AppRegistry.addUser("2", "John")
    AppRegistry.addUser("3", "Martin")
    println(s"Is user with ID=1 registered? ${AppRegistry.isUserRegistered("1")}")

    println("Removing ID=2")
    AppRegistry.removeUser("2")
    println(s"Is user with ID=2 registered? ${AppRegistry.isUserRegistered("2")}")

    println(s"All users registered are: ${AppRegistry.getAllUserNames.mkString(",")}")
  }
}
