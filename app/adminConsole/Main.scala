package adminConsole

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

object Main extends JFXApp {
  val mongoClient:MongoClient = MongoClient("mongodb://duane:duane@ds129723.mlab.com:29723/qacinema")
  val mongoDB:MongoDatabase = mongoClient.getDatabase("qacinema")
  val movies:MongoCollection[Document] = mongoDB.getCollection("movies")

  val priSta:PrimaryStage = new PrimaryStage()

  val startScreen:startScreen = new startScreen()
  val moviesScreen:moviesScreen = new moviesScreen()
  val newMoviesScreen:newMoviesScreen = new newMoviesScreen()

  def changeView(input:String):Unit = {
    input match {
      case "Main" => priSta.scene = startScreen
        priSta.width = 400
        priSta.height = 340
        priSta.title = "Main"
      case "movies" => priSta.scene = moviesScreen
        priSta.width = 1000
        priSta.height = 740
        priSta.title = "All Movies"
        moviesScreen.updatePage()
      case "receipts" => println("Receipts Page")
      case "newMovie" => priSta.scene = newMoviesScreen
        priSta.width = 1000
        priSta.height = 740
        priSta.title = "New Movies"
        newMoviesScreen.updatePage()
    }
    priSta.centerOnScreen()
  }
  changeView("newMovie")
}
