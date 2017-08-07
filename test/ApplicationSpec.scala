import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 400 on an invalid request" in new WithApplication{
      val fake = route(FakeRequest(GET, "/boum"))
      status(fake.get) must equalTo(NOT_FOUND)
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
    }

    "render the listings page" in new WithApplication{
      val listing = route(FakeRequest(GET, "/listing")).get
      status(listing) must equalTo(OK)
      contentType(listing) must beSome.which(_ == "text/html")
    }

    "render the about us page" in new WithApplication{
      val about = route(FakeRequest(GET, "/about")).get
      status(about) must equalTo(OK)
      contentType(about) must beSome.which(_ == "text/html")
      contentAsString(about) must contain("This is a header")
    }

    "render the deals page" in new WithApplication{
      val deals = route(FakeRequest(GET, "/deals")).get
      status(deals) must equalTo(OK)
      contentType(deals) must beSome.which(_ == "text/html")
    }

    "render the movieInfo page" in new WithApplication{
      val movieInfo = route(FakeRequest(GET, "/movieInfo?movieID=315635")).get
      status(movieInfo) must equalTo(OK)
      contentType(movieInfo) must beSome.which(_ == "text/html")
    }

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
