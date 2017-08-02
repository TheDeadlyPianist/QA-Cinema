package models

import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class ContactDetails(email: String, name: String, subject: String, content: String)

object ContactDetails{
  val contactForm =
    mapping("email" -> nonEmptyText(5,56),
      "name" -> nonEmptyText,
      "subject" -> nonEmptyText,
      "content" -> nonEmptyText
    )(ContactDetails.apply)(ContactDetails.unapply)
  }

