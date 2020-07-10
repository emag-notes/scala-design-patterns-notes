package sdp.ch11.implicits

package object di {
  implicit val databaseService: DatabaseService = new DatabaseServiceImpl
  implicit val userService: UserService         = new UserServiceImpl
}
