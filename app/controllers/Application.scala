package controllers

import play.api._
import play.api.mvc._
import scalaEnum.seatingPlanArray._

class Application extends Controller {

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
    Ok(views.html.seatingSystem(useSeats))
  }

  def contactUs = Action {
    Ok(views.html.contactUs())
  }

}