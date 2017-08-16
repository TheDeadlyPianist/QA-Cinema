package adminConsole

import scalafx.scene.Scene
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.StackPane
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.Includes._

class startScreen extends Scene {
  fill = Color.DarkBlue.darker
  stylesheets += this.getClass.getResource("admin.css").toExternalForm
  val receiptText:Text = new Text("Receipts"){style="-fx-font-size: 30pt;"}
  val movieText:Text = new Text("Movies"){style="-fx-font-size: 30pt;"}

  val receiptContainer:StackPane = new StackPane() {
    children.addAll(receiptText)
    minWidth = 400
    minHeight = 150
    id="selection"
  }
  val movieContainer:StackPane = new StackPane() {
    children.addAll(movieText)
    relocate(0, 150)
    minWidth = 400
    minHeight = 150
    id="selection"
  }

  receiptContainer.onMouseClicked = (e:MouseEvent) => {
    Main.changeView("receipts")
  }

  movieContainer.onMouseClicked = (e:MouseEvent) => {
    Main.changeView("movies")
  }

  content = List(receiptContainer, movieContainer)
}
