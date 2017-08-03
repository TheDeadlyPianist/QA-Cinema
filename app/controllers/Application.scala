package controllers

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}
import org.mongodb.scala.model.Updates._
import controllers.Helpers._
import play.api._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scalaEnum.seatingPlanArray._
import scalaj.http.Http

class Application extends Controller {

  val mongoClient:MongoClient = MongoClient("mongodb://duane:duane@ds129723.mlab.com:29723/qacinema")
  val mongoDB:MongoDatabase = mongoClient.getDatabase("qacinema")
  val receipts:MongoCollection[Document] = mongoDB.getCollection("receipts")
  val movies:MongoCollection[Document] = mongoDB.getCollection("movies")

  def index = Action {
    var pushArray:ArrayBuffer[Map[String, String]] = ArrayBuffer()

    val pullDB = Future{movies.find().results()}
    pullDB.onSuccess {
      case result => result
    }
    val moviesInDB = Await.result[Seq[Document]](pullDB, 10 seconds)

    for(i <- 0 until moviesInDB.length) {
      println(moviesInDB(i))
      val newName = moviesInDB(i)("title").asString().getValue
      val newYear = moviesInDB(i)("year").asInt32().getValue
      println(newYear)
      val newURL = s"https://api.themoviedb.org/3/search/movie?api_key=324938bccc324fb58e236a92cb0a9bc3&language=en-US&query=$newName&page=1&include_adult=true&year=$newYear".replace(" ", "%20")
      println(newURL)
      val stuffs = Future{Http(newURL).asString}
      stuffs.onSuccess{
        case result => result
      }
      val returnV = Json.parse(Await.result(stuffs, 10 seconds).body)

      pushArray += Map("title" -> ((returnV \ "results")(0)\"title").as[String], "imageUrl" -> ("https://image.tmdb.org/t/p/original" + ((returnV \ "results")(0)\"backdrop_path").as[String]))
    }

    Ok(views.html.index("Index: Success")(pushArray.toArray))
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