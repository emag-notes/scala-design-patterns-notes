package sdp.ch12.job_scheduler.scheduler.dao

import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sdp.ch12.job_scheduler.scheduler.TestEnvironment

import scala.util.Using

class DaoServiceTest extends AnyFlatSpec with Matchers with BeforeAndAfter with TestEnvironment {

  override val databaseService: DatabaseService   = new H2DatabaseService
  override val migrationService: MigrationService = new MigrationService
  override val daoService: DaoService             = new DaoServiceImpl

  before {
    // we run this here. Generally migrations will only
    // be dealing with data layout and we well be able to have
    // test classes that insert test data.
    migrationService.runMigrations()
  }

  after {
    migrationService.cleanupDatabase()
  }

  "readResultSet" should "properly iterate over a result set and apply a function to it." in {
    Using(daoService.getConnection) { connection =>
      daoService.executeSelect(
        connection.prepareStatement(
          "SELECT name FROM people"
        )
      ) { rs => daoService.readResultSet(rs) { row => row.getString("name") } }
    }.fold(
      err => fail(err),
      result => {
        result should have size (3)
        result should contain("Ivan")
        result should contain("Maria")
        result should contain("John")
      }
    )

  }
}
