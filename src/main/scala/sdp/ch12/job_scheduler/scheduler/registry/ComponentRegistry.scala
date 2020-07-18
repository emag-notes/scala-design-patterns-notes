package sdp.ch12.job_scheduler.scheduler.registry

import sdp.ch12.job_scheduler.scheduler.actors.{ActorFactory, ActorFactoryComponent}
import sdp.ch12.job_scheduler.scheduler.config.app.AppConfigComponent
import sdp.ch12.job_scheduler.scheduler.dao.{
  DaoService,
  DaoServiceComponent,
  DatabaseService,
  DatabaseServiceComponent,
  MigrationComponent
}
import sdp.ch12.job_scheduler.scheduler.io.IOServiceComponent
import sdp.ch12.job_scheduler.scheduler.services.JobConfigReaderServiceComponent

object ComponentRegistry
    extends AppConfigComponent
    with IOServiceComponent
    with JobConfigReaderServiceComponent
    with DatabaseServiceComponent
    with MigrationComponent
    with DaoServiceComponent
    with ActorFactoryComponent {

  override val appConfigService: ComponentRegistry.AppConfigService             = new AppConfigService
  override val ioService: ComponentRegistry.IOService                           = new IOService
  override val jobConfigReaderService: ComponentRegistry.JobConfigReaderService = new JobConfigReaderService
  override val databaseService: DatabaseService                                 = new H2DatabaseService
  override val migrationService: ComponentRegistry.MigrationService             = new MigrationService
  override val daoService: DaoService                                           = new DaoServiceImpl
  override val actorFactory: ActorFactory                                       = new ActorFactoryImpl
}
