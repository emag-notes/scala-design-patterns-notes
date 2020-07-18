package sdp.ch12.job_scheduler.scheduler.config.job

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.{Duration, FiniteDuration}

final case class TimeOptions(hours: Int, minutes: Int) {
  require(0 <= hours || hours <= 23, s"Hours must be between 0 and 23: $hours")
  require(0 <= minutes || minutes <= 59, s"Minutes must be between 0 and 59: $minutes")

  def getInitialDelay(now: LocalDateTime, frequency: JobFrequency): FiniteDuration = {
    val firstRun = now.withHour(hours).withMinute(minutes)
    val isBefore = firstRun.isBefore(now)
    val actualFirstRun = frequency match {
      case Hourly =>
        var tmp = firstRun
        Iterator
          .continually { tmp = tmp.plusHours(1); tmp }
          .takeWhile(d => d.isBefore(now))
          .toList
          .lastOption
          .getOrElse(if (isBefore) firstRun else firstRun.minusHours(1))
          .plusHours(1)

      case Daily =>
        var tmp = firstRun
        Iterator
          .continually { tmp = tmp.plusDays(1); tmp }
          .takeWhile(d => d.isBefore(now))
          .toList
          .lastOption
          .getOrElse(if (isBefore) firstRun else firstRun.minusDays(1))
          .plusDays(1)
    }
    val secondsUnitRun = now.until(actualFirstRun, ChronoUnit.SECONDS)
    Duration.create(secondsUnitRun, TimeUnit.SECONDS)
  }
}
