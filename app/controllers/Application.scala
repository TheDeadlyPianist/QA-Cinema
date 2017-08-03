package controllers

import javax.inject.Inject

import models.ContactDetails
import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, MessagesApi}

import scalaEnum.seatingPlanArray._

class Application @Inject()(val messagesApi: MessagesApi)extends Controller with I18nSupport {

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

  def contactUs = Action { implicit request =>
    Ok(views.html.contactUs(ContactDetails.contactForm))
  }

  def submitForm = Action { implicit request =>
    val formValidationResult = ContactDetails.contactForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.contactUs(formWithErrors))
    }, { contactDetails =>
      Ok(views.html.contactUs(formValidationResult))
    })
  }


}