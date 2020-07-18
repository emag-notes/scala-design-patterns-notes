package sdp.ch12.job_scheduler.scheduler.services

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import org.json4s._
import org.json4s.jackson.JsonMethods._
import sdp.ch12.job_scheduler.scheduler.config.app.AppConfigComponent
import sdp.ch12.job_scheduler.scheduler.config.job.{JobConfig, JobFrequencySerializer, JobTypeSerializer}
import sdp.ch12.job_scheduler.scheduler.io.IOServiceComponent

import scala.util.control.NonFatal

trait JobConfigReaderServiceComponent {
  this: AppConfigComponent with IOServiceComponent =>

  val jobConfigReaderService: JobConfigReaderService

  class JobConfigReaderService extends LazyLogging {
    private val customSerializers = List(
      JobFrequencySerializer,
      JobTypeSerializer
    )
    implicit val formats: Formats = DefaultFormats ++ customSerializers + JobConfig.jobConfigFieldSerializer

    def readJobConfigs: List[JobConfig] =
      ioService.getAllFilesWithExtension(appConfigService.configPath, appConfigService.configExtension).flatMap {
        path =>
          try {
            val config = parse(FileInput(new File(path))).extract[JobConfig]
            Some(config)
          } catch {
            case NonFatal(ex) =>
              logger.error("Error reading config: {}", path, ex)
              None
          }
      }
  }

}
