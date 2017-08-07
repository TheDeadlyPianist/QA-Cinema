import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.mvc.Results
import play.api.test.Helpers.{GET, OK, contentType, route, status}
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

@RunWith(classOf[JUnitRunner])
class LoginSpec extends PlaySpecification with Results {
  "Login" should {
    "render the register page" in new WithApplication {
      val deals = route(FakeRequest(GET, "/register")).get
      status(deals) must equalTo(OK)
      contentType(deals) must beSome.which(_ == "text/html")
    }

    "render the login page" in new WithApplication {
      val deals = route(FakeRequest(GET, "/login")).get
      status(deals) must equalTo(OK)
      contentType(deals) must beSome.which(_ == "text/html")
    }
  }
}