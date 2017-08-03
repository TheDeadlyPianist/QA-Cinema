package models

import play.api.data.{Form, Mapping}
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class ContactDetails(email: String, name: String, subject: String, content: String)

object ContactDetails{

  val emailValidation: Mapping[String] = nonEmptyText.verifying("Must be a valid email",
    email => email.matches("""^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})"""))

  val contactForm = Form(
    mapping(
      "email" -> emailValidation,
      "name" -> nonEmptyText,
      "subject" -> nonEmptyText,
      "content" -> nonEmptyText
    )(ContactDetails.apply)(ContactDetails.unapply)
  )
}

