package controllers

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}
import org.mongodb.scala.model.Updates._
import controllers.Helpers._
import play.api._
import play.api.mvc._

import scalaEnum.seatingPlanArray._

class Application extends Controller {

  val mongoClient:MongoClient = MongoClient("mongodb://duane:pass@ds129723.mlab.com:29723/qacinema")
  val mongoDB:MongoDatabase = mongoClient.getDatabase("qacinema")
  val receipts:MongoCollection[Document] = mongoDB.getCollection("receipts")
  val movies:MongoCollection[Document] = mongoDB.getCollection("movies")

  println("results: " + receipts.find())

  def index = Action {
    val exampleMovieArray:Array[Map[String, String]] = Array(Map("title" -> "Logan", "imageUrl" -> "http://spartanoracle.com/wp-content/uploads/2017/05/logan.jpg"), Map("title" -> "Spiderman Homecomeing", "imageUrl" -> "http://www.sonypictures.com/movies/spidermanhomecoming/assets/images/6216f0d376efe2bf2645ba6b9d941a45eaabf50e.jpg"))

    val dbArray = Array(Document("title" -> "Logan"), Document("title" -> "Spiderman Homecoming"))
//
//    val moviesInDB = movies.find().results()
//    println(moviesInDB)

    Ok(views.html.index("Index: Success")(exampleMovieArray))
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
    var letterMap: Map[Int, Char] = Map()
    val lengthOfSeats = useSeats.count(_ == 2) + 1
    val numbers: Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    val letters: Array[Char] = Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z').slice(0, lengthOfSeats).reverse
    for(i <- 0 until lengthOfSeats) {
      letterMap += numbers(i) -> letters(i)
    }

    var timeList:Array[String] = Array("9:00", "11:00")

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