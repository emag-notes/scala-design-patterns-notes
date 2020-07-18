package sdp.ch12.job_scheduler.scheduler.config.app

import com.typesafe.config.ConfigFactory

trait AppConfigComponent {
  val appConfigService: AppConfigService

  class AppConfigService {
    //-Dconfig.resource=production.conf for overriding
    private val conf    = ConfigFactory.load()
    private val appConf = conf.getConfig("job-scheduler")
    private val db      = appConf.getConfig("db")

    val configPath: String      = appConf.getString("config-path")
    val configExtension: String = appConf.getString("config-extension")
    val workers: Int            = appConf.getInt("workers")

    val dbConnectionString: String = db.getString("connection-string")
    val dbUsername: String         = db.getString("username")
    val dbPassword: String         = db.getString("password")
  }
}
