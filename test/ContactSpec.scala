import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.mvc.Results
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

@RunWith(classOf[JUnitRunner])
class ContactSpec extends PlaySpecification with Results {
  "Contact" should {

    "render the ContactUs page" in new WithApplication() {
      val contact = route(FakeRequest(GET, "/contactUs"))
      status(contact.get) must equalTo(BAD_REQUEST) //bad request because it tries to submit the form on initial load
      contentType(contact.get) must beSome.which(_ == "text/html")
    }

    "incorrect details should return bad_request, invalid email" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test", "name" -> "test",
        "subject" -> "", "content" -> "fail condition" ))
      status(request.get) should equalTo(BAD_REQUEST)
      contentType(request.get) must beSome.which(_ == "text/html")
    }


    "incorrect details should return bad_request, invalid name" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test", "name" -> "",
        "subject" -> "test", "content" -> "fail condition" ))
      status(request.get) should equalTo(BAD_REQUEST)
      contentType(request.get) must beSome.which(_ == "text/html")
    }

    "incorrect details should return bad_request, invalid subject" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test", "name" -> "test",
        "subject" -> "", "content" -> "fail condition" ))
      status(request.get) should equalTo(BAD_REQUEST)
      contentType(request.get) must beSome.which(_ == "text/html")
    }

    "incorrect details should return bad_request, invalid content, less than 10" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test", "name" -> "test",
        "subject" -> "", "content" -> (1 to 9).toString))
      status(request.get) should equalTo(BAD_REQUEST)
      contentType(request.get) must beSome.which(_ == "text/html")
    }

    "incorrect details should return bad_request, invalid content, greater than 500" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test", "name" -> "test",
        "subject" -> "", "content" -> (1 to 502).toString))
      status(request.get) should equalTo(BAD_REQUEST)
      contentType(request.get) must beSome.which(_ == "text/html")
    }

    "correct details should return see other" in new WithApplication() {
      val request = route(FakeRequest(POST, "/contactUs").withFormUrlEncodedBody("email" -> "test@gmail.com", "name" -> "test",
        "subject" -> "test", "content" -> "This should pass" ))
      status(request.get) should equalTo(SEE_OTHER)
    }
  }
}