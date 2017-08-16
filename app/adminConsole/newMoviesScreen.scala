package adminConsole

import java.util.Date

import controllers.Helpers._
import org.h2.engine.DbObject
import org.mongodb.scala.Document
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.parsing.json.JSONArray
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, ListView, TextField}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{HBox, StackPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalaj.http.Http

class newMoviesScreen extends Scene {
  fill = Color.DarkBlue.darker
  stylesheets += this.getClass.getResource("admin.css").toExternalForm

  val mar20:Rectangle = new Rectangle(){width=20; height=20;fill=Color.Transparent}

  val idBoxLabel:Text = new Text("MovieDB ID: "){styleClass+="itemText"}
  val idBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val nameBoxLabel:Text = new Text("Movie Name: "){styleClass+="itemText"}
  val nameBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val releaseYearBoxLabel:Text = new Text("Year of release: "){styleClass+="itemText"}
  val releaseYearBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val genreBoxLabel:Text = new Text("Genre: "){styleClass+="itemText"}
  val genreBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val lengthBoxLabel:Text = new Text("Running time: "){styleClass+="itemText"}
  val lengthBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val ratingBoxLabel:Text = new Text("Film rating: "){styleClass+="itemText"}
  val ratingBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val ageBoxLabel:Text = new Text("Age rating: "){styleClass+="itemText"}
  val ageBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val startBoxLabel:Text = new Text("Start Date: "){styleClass+="itemText"}
  val startBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val endBoxLabel:Text = new Text("End Date: "){styleClass+="itemText"}
  val endBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val screenBoxLabel:Text = new Text("Screen Number: "){styleClass+="itemText"}
  val screenBox:ComboBox[Int] = new ComboBox[Int](List(1, 2, 3, 4, 5, 6, 7, 8))

  val searchBarLabel:Text = new Text("Search Movie Name: ")
  val searchBarInput:TextField = new TextField(){maxWidth=360;minWidth=360}
  val searchBarButton:Button = new Button("Search")

  val moviePullBar:HBox = new HBox(){
    minWidth = 600
    maxWidth = 600
    minHeight = 70
    relocate(260, 50)
    styleClass += "viewingArea"
    children.addAll(searchBarLabel, searchBarInput, searchBarButton)
  }

  val viewingArea:VBox = new VBox(){
    minWidth = 600
    minHeight = 500
    relocate(260, 150)
    styleClass += "viewingArea"
    val idRow:HBox = new HBox(){children.addAll(idBoxLabel, idBox)}
    val nameRow:HBox = new HBox(){children.addAll(nameBoxLabel, nameBox)}
    val releaseDateRow:HBox = new HBox(){children.addAll(releaseYearBoxLabel, releaseYearBox)}
    val genreRow:HBox = new HBox(){children.addAll(genreBoxLabel, genreBox)}
    val lengthRow:HBox = new HBox(){children.addAll(lengthBoxLabel, lengthBox)}
    val ratingRow:HBox = new HBox(){children.addAll(ratingBoxLabel, ratingBox)}
    val ageRow:HBox = new HBox(){children.addAll(ageBoxLabel, ageBox)}
    val startRow:HBox = new HBox(){children.addAll(startBoxLabel, startBox)}
    val endRow:HBox = new HBox(){children.addAll(endBoxLabel, endBox)}
    val screenRow:HBox = new HBox(){children.addAll(screenBoxLabel, screenBox)}

    children.addAll(idRow, nameRow, releaseDateRow, genreRow, lengthRow, ratingRow, ageRow, startRow, endRow, screenRow)
  }

  val backButton:StackPane = new StackPane(){
    id="selection"
    styleClass += "backButton"
    minWidth = 100
    minHeight = 50
    val backText:Text = new Text("Back"){style="-fx-font-size: 30pt;"}
    children.addAll(
      backText
    )
    relocate(885, 0)
  }

  val createButton:StackPane = new StackPane(){
    id="selection"
    styleClass += "backButton"
    minWidth = 100
    minHeight = 50
    val backText:Text = new Text("Create New"){style="-fx-font-size: 20pt;"}
    children.addAll(
      backText
    )
    relocate(833, 650)
  }

  backButton.onMouseClicked = (e:MouseEvent) => {
    Main.changeView("movies")
  }

  searchBarButton.onMouseClicked = (e:MouseEvent) => {
    if (searchBarInput.getText.length != 0) {
      try {
        val movieName:String = searchBarInput.getText.replace(" ", "%20")
        val newURL = s"https://api.themoviedb.org/3/search/movie?api_key=324938bccc324fb58e236a92cb0a9bc3&language=en-US&query=" + movieName + "&page=1&include_adult=true"
        val futRet = Future {
          Http(newURL).asString
        }
        futRet.onSuccess {
          case result => result
        }
        val returnV = Json.parse(Await.result(futRet, 10 seconds).body)
        //Get more specific information about the movie
        val movieID = ((returnV \ "results") (0) \ "id").as[Int].toString
        val idURL = s"https://api.themoviedb.org/3/movie/$movieID?api_key=324938bccc324fb58e236a92cb0a9bc3&language=en-US"
        val futRet2 = Future {
          Http(idURL).asString
        }
        futRet2.onSuccess {
          case result => result
        }
        val allMovieInfo = Json.parse(Await.result(futRet2, 10 seconds).body)
        //Get age rating
        val ageURL = s"https://api.themoviedb.org/3/movie/$movieID/release_dates?api_key=324938bccc324fb58e236a92cb0a9bc3"
        val futRet3 = Future {
          Http(ageURL).asString
        }
        futRet3.onSuccess {
          case result => result
        }
        val ageInfo = Json.parse(Await.result(futRet3, 10 seconds).body)
        val certIndex = (ageInfo\"results"\\"iso_3166_1").map(i=>i.as[String].replace("\"", "")).indexOf("GB")
        val ageRating:String = (((((ageInfo\"results").get)(certIndex)\"release_dates").get)(0)\"certification").as[String]


        idBox.text = ((returnV \ "results") (0) \ "id").as[Int].toString
        nameBox.text = ((returnV \ "results") (0) \ "title").as[String]
        releaseYearBox.text = (1900 + (allMovieInfo \ "release_date").as[Date].getYear).toString
        lengthBox.text = (allMovieInfo \ "runtime").as[Int].toString
        ratingBox.text = (allMovieInfo \ "vote_average").as[Float].toString
        genreBox.text = (allMovieInfo \ "genres" \\ "name").toString.replace("\"", "").replace("ListBuffer(", "").replace(")", "")
        ageBox.text = ageRating
      } catch {
        case error => println("\n\n\nThere has been an issue.\n"+error.printStackTrace())
      }

    } else {}
  }

  createButton.onMouseClicked = (e:MouseEvent) => {

    val postQuery:Document = Document("apiID" -> idBox.getText.toInt,
      "title" -> nameBox.getText,
      "year" -> releaseYearBox.getText.toInt,
      "length" -> lengthBox.getText.toInt,
      "screen" -> screenBox.getSelectionModel.getSelectedItem,
      "startDate" -> startBox.getText.toInt,
      "endDate" -> endBox.getText.toInt,
      "rating" -> ratingBox.getText,
      "certification" -> ageBox.getText,
      "genres" -> genreBox.getText)
    val queryPush = Future{Main.movies.insertOne(postQuery).results()}
    queryPush.onSuccess{
      case result => result
    }
    val returnResult = Await.ready(queryPush, 10 seconds)
    println(returnResult)
    clearForm()
    Main.changeView("movies")
  }

  def updatePage():Unit = {

  }
  def clearForm():Unit = {

  }

  content = List(backButton, createButton, viewingArea, moviePullBar)
}
