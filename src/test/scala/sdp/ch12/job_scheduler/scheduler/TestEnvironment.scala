package sdp.ch12.job_scheduler.scheduler

import org.scalatestplus.mockito.MockitoSugar
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
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

trait TestEnvironment
    extends AppConfigComponent
    with IOServiceComponent
    with JobConfigReaderServiceComponent
    with DatabaseServiceComponent
    with MigrationComponent
    with DaoServiceComponent
    with ActorFactoryComponent
    with MockitoSugar {

  // use the test configuration file.
  override val appConfigService: AppConfigService = spy(new AppConfigService)
  // override the path here to use the test resources.
  when(appConfigService.configPath).thenReturn(getClass.getResource("/").getPath)

  override val ioService: IOService                           = mock[IOService]
  override val jobConfigReaderService: JobConfigReaderService = mock[JobConfigReaderService]
  override val databaseService: DatabaseService               = mock[DatabaseService]
  override val migrationService: MigrationService             = mock[MigrationService]
  override val daoService: DaoService                         = mock[DaoService]
  override val actorFactory: ActorFactory                     = mock[ActorFactory]
}
