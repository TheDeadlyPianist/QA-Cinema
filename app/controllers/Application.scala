package controllers

import models.ContactDetails
import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, MessagesApi}
import javax.inject.Inject

import play.api.libs.mailer._

import scalaEnum.seatingPlanArray._


class Application @Inject()(val messagesApi: MessagesApi, mailerClient: MailerClient)
  extends Controller with I18nSupport {

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
      Ok(views.html.contactUs(ContactDetails.contactForm))
    })
  }
}