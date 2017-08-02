package controllers

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}
import org.mongodb.scala.model.Updates._
import play.api._
import play.api.mvc._

import scalaEnum.seatingPlanArray._

class Application extends Controller {

//  val mongoClient:MongoClient = MongoClient()
//  val mongoDB:MongoDatabase = mongoClient.getDatabase("mongodb://duane:pass@ds129723.mlab.com:29723/qacinema")
//  val receipts:MongoCollection[Document] = mongoDB.getCollection("receipts")

  def index = Action {
    Ok(views.html.index("Index: Success"))
  }

  def theListing = Action {
    Ok(views.html.listing("Listing: Success"))
  }

  def theAbout = Action {
    Ok(views.html.about("About: Success"))
  }

  def theDeals = Action {
    Ok(views.html.deals("Deals: Success"))
  }

  def seating(seatingPlan:String) = Action {
    val seatingObj:Map[String, Array[Int]] = Map("seats1" -> seats1, "seats2" -> seats2)
    val useSeats:Array[Int] = seatingObj(seatingPlan)
    var timeList:Array[String] = Array("9:00", "11:00")
    var letterMap: Map[Int, Char] = Map()
    val lengthOfSeats = useSeats.count(_ == 2) + 1
    val numbers: Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    val letters: Array[Char] = Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z').slice(0, lengthOfSeats).reverse
    for(i <- 0 until lengthOfSeats) {
      letterMap += numbers(i) -> letters(i)
    }

    var letN:Int = 0
    var rowN:Int = 0
    val seatLabels = useSeats.map(i => {
      i match {
        case 1 => rowN += 1; letterMap(letN) + "" + rowN
        case 2 => letN += 1; rowN=0; ""
        case 3 => ""
        case 4 => ""
        case _ => ""
      }
    })

    Ok(
      views.html.booking(useSeats)(lengthOfSeats)(seatLabels)(("name"->"Logan", "screen"->1, "length"->120))(timeList)
    )
  }
}