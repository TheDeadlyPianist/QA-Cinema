package controllers

import javax.inject.Inject

import models.ContactDetails
import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, MessagesApi}

import models.ContactDetails
import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, MessagesApi}
import javax.inject.Inject

import play.api.libs.mailer._

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

class Application @Inject()(val messagesApi: MessagesApi, mailerClient: MailerClient)
  extends Controller with I18nSupport {

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

      pushArray += Map("title" -> ((returnV \ "results")(0)\"title").as[String], "imageUrl" -> ("https://image.tmdb.org/t/p/original" + ((returnV \ "results")(0)\"backdrop_path").as[String]), "id" -> ((returnV \ "results")(0)\"id").as[Int].toString())
    }

    Ok(views.html.index("Index: Success")(pushArray.toArray))
  }

  def theListing = Action {
    Ok(views.html.listing("Listing: Success"))
  }


  def register = Action {
    Ok(views.html.register())
  }

  def login = Action {
    Ok(views.html.login())
  }

  def payment = Action {
    Ok(views.html.payment());
  }


  def theAbout = Action {
    Ok(views.html.about("About: Success"))
  }

  def theDeals = Action {
    Ok(views.html.deals("Deals: Success"))
  }

  def testAction = Action {
    Ok( "<script src=\"@routes.Assets.at(\"javascripts/stripePayment.js\")\" rel=\"javascript\"></script>").as("text/html")
  }

  def seating(filmName:String) = Action {

    val searchQuery = Document("title" -> filmName)
    val getFilmDocument = Future{movies.find(searchQuery).results()}
    getFilmDocument.onSuccess {
      case result => result
    }
    val seatingPlan = "screen" + Await.result(getFilmDocument, 10 seconds)(0)("screen").asInt32().intValue()

    val seatingObj:Map[String, Array[Int]] = Map("screen1" -> seats1, "screen2" -> seats2)
    val useSeats:Array[Int] = seatingObj(seatingPlan)
    var letterMap: Map[Int, Char] = Map()
    val lengthOfSeats = useSeats.count(_ == 2) + 1
    val numbers: Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    val letters: Array[Char] = Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z').slice(0, lengthOfSeats).reverse
    for(i <- 0 until lengthOfSeats) {
      letterMap += numbers(i) -> letters(i)
    }
    //=============================================================================================================================================================================================

    val times:Array[Int] = Array(540, 1410)
    val filmLength = Await.result(getFilmDocument, 10 seconds)(0)("length").asInt32().intValue() + 20

    val timeList:Seq[String] = for(i <- times(0) to times(1) by filmLength if(i +filmLength < times(1))) yield {
      var newInt = i
      var hours = 0
      while(newInt >= 60) {
        newInt -= 60
        hours += 1
      }
      var minutes:String = if(newInt < 10) {s"0$newInt"} else {newInt.toString()}

      hours + ":" + minutes
    }

    //=============================================================================================================================================================================================
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
      views.html.booking(useSeats)(lengthOfSeats)(seatLabels)(filmName)(timeList)
    )
  }
  def sendEmail(from: String, name: String, subject: String, text: String): Unit ={
    val email = Email(
      subject,
      from,
      Seq("To <Qaodeon@gmail.com>"),
      bodyText = Some(text + "    From: " + name + " Email: " + from )
    )
    mailerClient.send(email)
  }

  def submitForm = Action { implicit request =>
    val formValidationResult = ContactDetails.contactForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.contactUs(formWithErrors))
    }, { contactDetails =>
      sendEmail(contactDetails.email, contactDetails.name, contactDetails.subject, contactDetails.content)
      Redirect("/contactUs").flashing("messageSent" -> "Thank You. Your message has been sent")
    })
  }
  def theMovieInfo(movieID:Int) = Action {
    var p:ArrayBuffer[Map[String, String]] = ArrayBuffer()

    val newURL = s"https://api.themoviedb.org/3/movie/$movieID?api_key=324938bccc324fb58e236a92cb0a9bc3".replace(" ", "%20")
    println(newURL)

    val stuffs = Future{Http(newURL).asString}
    stuffs.onSuccess{
        case result => result
    }
    val returnV = Json.parse(Await.result(stuffs, 10 seconds).body)

    p += Map("title" -> (returnV \ "title").as[String], "imageUrl" -> ("https://image.tmdb.org/t/p/original" + (returnV \ "poster_path").as[String]))



    Ok(views.html.moviesInfo("MovieInfo: Success")(p.toArray))
  }
}