package sdp.ch11.cake

import java.sql.{Connection, SQLException}

import sdp.ch11.cake.modell.{Class, Person}

import scala.util.Using

trait MigrationComponent {
  this: DatabaseComponent =>

  val migrationService: MigrationService

  class MigrationService {

    def runMigration(): Unit =
      Using(databaseService.getConnection) { connection =>
        // create tables
        createPeopleTable(connection)
        createClassesTable(connection)
        createPeopleToClassesTable(connection)
        // populate
        insertPeople(
          connection,
          List(
            Person(1, "Ivan", 26),
            Person(2, "Maria", 25),
            Person(3, "John", 27)
          )
        )
        insertClasses(
          connection,
          List(
            Class(1, "Scala Design Patterns"),
            Class(2, "Java Programming"),
            Class(3, "Mountain Biking")
          )
        )
        signPeopleToClasses(
          connection,
          List(
            (1, 1),
            (1, 2),
            (1, 3),
            (2, 1),
            (3, 1),
            (3, 3)
          )
        )
      }.fold(
        err => throw new SQLException(err),
        _ => ()
      )

    private def executeSQL(sql: String, connection: Connection): Unit =
      Using(connection.prepareStatement(sql))(_.execute())
        .fold(
          err => throw new SQLException(err),
          _ => ()
        )

    private def createPeopleTable(connection: Connection): Unit = {
      val sql =
        """
          |CREATE TABLE people(
          |  id INT PRIMARY KEY,
          |  name VARCHAR(255) NOT NULL,
          |  age INT NOT NULL
          |)
        """.stripMargin
      executeSQL(sql, connection)
    }

    private def createClassesTable(connection: Connection): Unit = {
      val sql =
        """
          |CREATE TABLE classes(
          |  id INT PRIMARY KEY,
          |  name VARCHAR(255) NOT NULL
          |)
        """.stripMargin
      executeSQL(sql, connection)
    }

    private def createPeopleToClassesTable(connection: Connection): Unit = {
      val sql =
        """
          |CREATE TABLE people_classes(
          |  person_id INT NOT NULL,
          |  class_id INT NOT NULL,
          |  PRIMARY KEY(person_id, class_id),
          |  FOREIGN KEY(person_id) REFERENCES people(id) ON DELETE CASCADE ON UPDATE CASCADE,
          |  FOREIGN KEY(class_id) REFERENCES classes(id) ON DELETE CASCADE ON UPDATE CASCADE
          |)
        """.stripMargin
      executeSQL(sql, connection)
    }

    private def insertPeople(connection: Connection, people: List[Person]): Unit = {
      val sql = "INSERT INTO people(id, name, age) VALUES (?, ?, ?)"

      Using(connection.prepareStatement(sql)) { statement =>
        people.foreach { person =>
          statement.setInt(1, person.id)
          statement.setString(2, person.name)
          statement.setInt(3, person.age)
          statement.addBatch()
        }
        statement.executeBatch()
      }.fold(
        err => throw new SQLException(err),
        _ => ()
      )
    }

    private def insertClasses(connection: Connection, classes: List[Class]): Unit = {
      val sql = "INSERT INTO classes(id, name) VALUES (?, ?)"

      Using(connection.prepareStatement(sql)) { statement =>
        classes.foreach { classs =>
          statement.setInt(1, classs.id)
          statement.setString(2, classs.name)
          statement.addBatch()
        }
        statement.executeBatch()
      }.fold(
        err => throw new SQLException(err),
        _ => ()
      )
    }

    private def signPeopleToClasses(connection: Connection, peopleToClasses: List[(Int, Int)]): Unit = {
      val sql = "INSERT INTO people_classes(person_id, class_id) VALUES (?, ?)"

      Using(connection.prepareStatement(sql)) { statement =>
        peopleToClasses.foreach {
          case (personId, classId) =>
            statement.setInt(1, personId)
            statement.setInt(2, classId)
            statement.addBatch()
        }
        statement.executeBatch()
      }.fold(
        err => throw new SQLException(err),
        _ => ()
      )
    }
  }
}
