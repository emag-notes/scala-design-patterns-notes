package sdp.ch11.lens

import scalaz.{Lens, LensFamily}

final case class Country(name: String, code: String)
final case class City(name: String, country: Country)
final case class Address(number: Int, street: String, city: City)
final case class Company(name: String, address: Address)
final case class User(name: String, company: Company, address: Address)

object UserVerboseExample {
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

    val ivanFixed = ivan.copy(
      company = ivan.company.copy(
        address = ivan.company.address.copy(
          city = ivan.company.address.city.copy(
            country = ivan.company.address.city.country.copy(
              code = ivan.company.address.city.country.code.toUpperCase
            )
          )
        )
      )
    )
    println(ivanFixed)
  }
}

object User {
  val userCompany: Lens[User, Company] = Lens.lensu[User, Company](
    (u, company) => u.copy(company = company),
    _.company
  )

  val userAddress: Lens[User, Address] = Lens.lensu[User, Address](
    (u, address) => u.copy(address = address),
    _.address
  )

  val companyAddress: Lens[Company, Address] = Lens.lensu[Company, Address](
    (c, address) => c.copy(address = address),
    _.address
  )

  val addressCity: Lens[Address, City] = Lens.lensu[Address, City](
    (a, city) => a.copy(city = city),
    _.city
  )

  val cityCountry: Lens[City, Country] = Lens.lensu[City, Country](
    (c, country) => c.copy(country = country),
    _.country
  )

  val countryCode: Lens[Country, String] = Lens.lensu[Country, String](
    (c, code) => c.copy(code = code),
    _.code
  )

  val userCompanyCountryCode: LensFamily[User, User, String, String] =
    userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode

  val userCompanyCountryCodeCompose: LensFamily[User, User, String, String] =
    countryCode <=< cityCountry <=< addressCity <=< companyAddress <=< userCompany
}

object UserLensExample {
  import User._

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

    val ivanFixed = userCompanyCountryCode.mod(_.toUpperCase, ivan)
    println(ivanFixed)
  }
}
