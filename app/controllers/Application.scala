package controllers

import play.api._
import play.api.mvc._

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

}