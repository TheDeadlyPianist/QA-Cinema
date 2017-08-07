import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class BookingSpec extends Specification {
  "seatBooking" should {
    "render the seat booking page" in new WithApplication {
      val deals = route(FakeRequest(GET, "/seatBooking?filmName=Logan&date=20170807&time=9:00")).get
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
