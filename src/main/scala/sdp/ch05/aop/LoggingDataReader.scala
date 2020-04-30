package sdp.ch05.aop
import sdp.ch05.aop.model.Person

trait LoggingDataReader extends DataReader {
  abstract override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result      = super.readData()
    val time        = System.currentTimeMillis() - startMillis
    println(s"readData took ${time} milliseconds.")
    result
  }

  abstract override def readDataInefficiently(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result      = super.readDataInefficiently()
    val time        = System.currentTimeMillis() - startMillis
    println(s"readDataInefficiently took ${time} milliseconds.")
    result
  }
}
