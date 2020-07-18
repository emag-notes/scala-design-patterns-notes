package sdp.ch12.job_scheduler.scheduler.services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sdp.ch12.job_scheduler.scheduler.TestEnvironment
import sdp.ch12.job_scheduler.scheduler.config.job.{Console, Daily, JobConfig, TimeOptions}

class JobConfigReaderServiceTest extends AnyFlatSpec with Matchers with TestEnvironment {

  override val ioService: IOService                           = new IOService
  override val jobConfigReaderService: JobConfigReaderService = new JobConfigReaderService

  "readJobConfigs" should "read and parse configrations successfully." in {
    val result = jobConfigReaderService.readJobConfigs
    result should have size (1)
    result should contain(
      JobConfig(
        "Test Command",
        "ping google.com -c 10",
        Console,
        Daily,
        TimeOptions(12, 10)
      )
    )
  }
}
