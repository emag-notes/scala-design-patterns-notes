package sdp.ch04.abstract_types

abstract class PrintData
abstract class PrintMaterial
abstract class PrintMedia

final case class Paper() extends PrintMedia
final case class Air()   extends PrintMedia

final case class Text()  extends PrintData
final case class Model() extends PrintData

final case class Toner()   extends PrintMaterial
final case class Plastic() extends PrintMaterial

trait Printer {
  type Data <: PrintData
  type Material <: PrintMaterial
  type Media <: PrintMedia

  def print(data: Data, material: Material, media: Media): String =
    s"Printing $data with $material material on $media media."
}

trait GenericPrinter[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia] {
  def print(data: Data, material: Material, media: Media): String =
    s"Printing $data with $material material on $media media."
}

class LaserPrinter extends Printer {
  override type Media    = Paper
  override type Data     = Text
  override type Material = Toner
}

class ThreeDPrinter extends Printer {
  override type Media    = Air
  override type Data     = Model
  override type Material = Plastic
}

class GenericLaserPrinter[Data <: Text, Material <: Toner, Media <: Paper] extends GenericPrinter[Data, Material, Media]
class GenericThreeDPrinter[Data <: Model, Material <: Plastic, Media <: Air]
    extends GenericPrinter[Data, Material, Media]

class GenericPrinterImpl[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia]
    extends GenericPrinter[Data, Material, Media]

object PrinterExample {
  def main(args: Array[String]): Unit = {
    val laser  = new LaserPrinter
    val threeD = new ThreeDPrinter

    println(laser.print(Text(), Toner(), Paper()))
    println(threeD.print(Model(), Plastic(), Air()))

    val genericLaser  = new GenericLaserPrinter[Text, Toner, Paper]
    val genericThreeD = new GenericThreeDPrinter[Model, Plastic, Air]

    println(genericLaser.print(Text(), Toner(), Paper()))
    println(genericThreeD.print(Model(), Plastic(), Air()))

    val wrongPrinter = new GenericPrinterImpl[Model, Toner, Air]
    println(wrongPrinter.print(Model(), Toner(), Air()))
  }
}
