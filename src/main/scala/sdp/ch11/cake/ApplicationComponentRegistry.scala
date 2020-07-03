package sdp.ch11.cake

object ApplicationComponentRegistry
    extends UserComponent
    with DaoComponent
    with DatabaseComponent
    with MigrationComponent {
  override val userService: ApplicationComponentRegistry.UserService           = new UserService
  override val dao: ApplicationComponentRegistry.Dao                           = new Dao
  override val databaseService: DatabaseService                                = new H2DatabaseService("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "")
  override val migrationService: ApplicationComponentRegistry.MigrationService = new MigrationService
}
