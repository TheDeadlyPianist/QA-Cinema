package adminConsole

import org.mongodb.scala.{Document}

import scalafx.scene.Scene
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{HBox, StackPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.scene.control.{ListView, TextField}
import scalafx.scene.shape.Rectangle
import controllers.Helpers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class moviesScreen extends Scene {
  fill = Color.DarkBlue.darker
  stylesheets += this.getClass.getResource("admin.css").toExternalForm

  val mar20:Rectangle = new Rectangle(){width=20; height=20;fill=Color.Transparent}

  val nameBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val nameBoxLabel:Text = new Text("Movie Name: "){styleClass+="itemText"}
  val releaseYearBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val releaseYearBoxLabel:Text = new Text("Year of release: "){styleClass+="itemText"}
  val genreBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val genreBoxLabel:Text = new Text("Genre: "){styleClass+="itemText"}
  val lengthBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val lengthBoxLabel:Text = new Text("Running time: "){styleClass+="itemText"}
  val ratingBox:TextField = new TextField(){maxWidth=400;minWidth=400}
  val ratingBoxLabel:Text = new Text("Film rating: "){styleClass+="itemText"}

  val allMovies:ListView[String] = new ListView[String]() {maxHeight = 600;minHeight=600;maxWidth=240;minWidth=240;layoutX=10;layoutY=50}

  val viewingArea:VBox = new VBox(){
    minWidth = 600
    minHeight = 600
    relocate(260, 50)
    styleClass += "viewingArea"
    val nameRow:HBox = new HBox(){children.addAll(nameBoxLabel, nameBox)}
    val releaseDateRow:HBox = new HBox(){children.addAll(releaseYearBoxLabel, releaseYearBox)}
    val genreRow:HBox = new HBox(){children.addAll(genreBoxLabel, genreBox)}
    val lengthRow:HBox = new HBox(){children.addAll(lengthBoxLabel, lengthBox)}
    val ratingRow:HBox = new HBox(){children.addAll(ratingBoxLabel, ratingBox)}

    children.addAll(nameRow, releaseDateRow, genreRow, lengthRow, ratingRow)
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

  val newMovieButton:StackPane = new StackPane(){
    id="selection"
    styleClass += "backButton"
    minWidth = 100
    minHeight = 50
    val newText:Text = new Text("New Film"){style="-fx-font-size: 20pt;"}
    children.addAll(
      newText
    )
    relocate(860, 650)
  }

  backButton.onMouseClicked = (e:MouseEvent) => {
    Main.changeView("Main")
  }
  newMovieButton.onMouseClicked = (e:MouseEvent) => {
    Main.changeView("newMovie")
  }
  allMovies.onMouseClicked = (e:MouseEvent) => {
    onSelection(allMovies.getSelectionModel().getSelectedItem)
  }
  def allMoviesPopulate():Array[String] = {
    val futWait = Future{Main.movies.find().results()}
    futWait.onSuccess {
      case result => result
    }
    val allMovieList = Await.result(futWait, 10 seconds)

    val returnArray:Array[String] = (for(i<-allMovieList.indices) yield {allMovieList(i)("title").asString().getValue}).toArray
    returnArray
  }

  def onSelection(input:String):Unit = {
    val searchQuery:Document = Document("title"->input)
    val futWait = Future{Main.movies.find(searchQuery).results()}
    futWait.onSuccess {
      case result => result
    }
    val queriedData = Await.result(futWait, 10 seconds)
    nameBox.text = queriedData(0)("title").asString().getValue
    releaseYearBox.text = queriedData(0)("year").asInt32().getValue.toString
    genreBox.text = queriedData(0)("title").asString().getValue
    lengthBox.text = queriedData(0)("length").asInt32().getValue.toString
    ratingBox.text = queriedData(0)("title").asString().getValue
  }

  def updatePage():Unit = {
    allMovies.items = new ListView[String](allMoviesPopulate()).items()
  }

  content = List(backButton, newMovieButton, viewingArea, allMovies)
}
