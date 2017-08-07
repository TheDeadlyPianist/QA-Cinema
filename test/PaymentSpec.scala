import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class PaymentSpec extends Specification {
  "payments" should {
    "render the seat payment page" in new WithApplication {
      val pay = route(FakeRequest(GET, "/payment")).get
      status(pay) must equalTo(OK)
      contentType(pay) must beSome.which(_ == "text/html")
    }
  }
}