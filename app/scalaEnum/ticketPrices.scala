package scalaEnum

object ticketPrices extends Enumeration {
  type tickets = Value

  val adult:Float = 7F
  val child:Float = 4F
  val oap:Float = 5.50F
  val reprobate:Float = 5.50F
}