package sdp.ch11.cake

import java.sql.{PreparedStatement, ResultSet, SQLException}

import sdp.ch11.cake.modell.{Class, Person}

import scala.util.Using

trait DaoComponent {
  this: DatabaseComponent =>

  val dao: Dao

  class Dao {
    def getPeople: List[Person] = {
      Using(databaseService.getConnection) { connection =>
        executeSelect(connection.prepareStatement("SELECT id, name, age FROM people")) { rs =>
          readResultSet(rs) { row => Person(row.getInt(1), row.getString(2), row.getInt(3)) }
        }
      }
    }.fold(
      err => throw new SQLException(err),
      suc => suc
    )

    def getClasses: List[Class] = {
      Using(databaseService.getConnection) { connection =>
        executeSelect(connection.prepareStatement("SELECT id, name FROM classes")) { rs =>
          readResultSet(rs) { row => Class(row.getInt(1), row.getString(2)) }
        }
      }
    }.fold(
      err => throw new SQLException(err),
      suc => suc
    )

    def getPeopleInClass(className: String): List[Person] = {
      Using(databaseService.getConnection) { connection =>
        val statement = connection.prepareStatement("""
            |SELECT p.id, p.name, p.age
            |FROM people p
            |INNER JOIN people_classes pc ON p.id = pc.person_id
            |INNER JOIN classes c ON c.id = pc.class_id
            |WHERE c.name = ?
          """.stripMargin)
        statement.setString(1, className)

        executeSelect(statement) {
          readResultSet(_) { row => Person(row.getInt(1), row.getString(2), row.getInt(3)) }
        }
      }
    }.fold(
      err => throw new SQLException(err),
      suc => suc
    )

    private def executeSelect[T](preparedStatement: PreparedStatement)(f: ResultSet => List[T]): List[T] =
      Using(preparedStatement) { ps => f(ps.executeQuery()) }.fold(
        err => throw new SQLException(err),
        identity
      )

    private def readResultSet[T](rs: ResultSet)(f: ResultSet => T): List[T] =
      Iterator
        .continually((rs.next(), rs))
        .takeWhile(_._1)
        .map {
          case (_, row) => f(row)
        }
        .toList
  }
}
