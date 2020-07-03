package sdp.ch11.lens.bad

final case class Country(var name: String, var code: String)
final case class City(var name: String, var country: Country)
final case class Address(var number: Int, var street: String, var city: City)
final case class Company(var name: String, var address: Address)
final case class User(var name: String, var company: Company, var address: Address)

object UserBadExamle {
  def main(args: Array[String]): Unit = {
    val uk               = Country("United Kingdom", "uk")
    val london           = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val castleBuilders   = Company("Castle Builders", buckinghamPalace)

    val switzerland   = Country("Swithzerland", "CH")
    val geneva        = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)

    val ivan = User("Ivan", castleBuilders, genevaAddress)
    println(ivan)

    println("Capitalize UK code...")

    ivan.company.address.city.country.code = ivan.company.address.city.country.code.toUpperCase
    println(ivan)
  }
}
