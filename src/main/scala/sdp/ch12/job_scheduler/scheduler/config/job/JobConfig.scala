package sdp.ch12.job_scheduler.scheduler.config.job

import org.json4s.FieldSerializer
import org.json4s.JsonAST.JField

final case class JobConfig(
    name: String,
    command: String,
    jobType: JobType,
    frequency: JobFrequency,
    timeOptions: TimeOptions
)

object JobConfig {
  val jobConfigFieldSerializer: FieldSerializer[JobConfig] = FieldSerializer[JobConfig](
    {
      case ("timeOptions", x) => Some("time_options", x)
      case ("jobType", x)     => Some("type", x)
    }, {
      case JField("time_options", x) => JField("timeOptions", x)
      case JField("type", x)         => JField("jobType", x)
    }
  )
}
