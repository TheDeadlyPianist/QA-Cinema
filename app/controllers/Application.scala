package controllers

import play.api._
import play.api.mvc._
import scalaEnum.seatingPlanArray._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def seating(seatingPlan:String) = Action {
    val seatingObj:Map[String, Array[Int]] = Map("seats1" -> seats1, "seats2" -> seats2)
    val useSeats:Array[Int] = seatingObj(seatingPlan)
    Ok(views.html.seatingSystem(useSeats))
  }

}