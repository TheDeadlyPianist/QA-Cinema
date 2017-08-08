import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.openqa.selenium.By
import play.api.test._
import play.api.test.Helpers._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "Load the navbar and navigate perfectly with all of the navbar's links" in new WithBrowser {
      //Connects to webpage and ensures the title is "Home", comfirming /index has loaded
      browser.goTo("http://localhost:" + "9000")
      browser.title() must contain("Home")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/")

      //Asserts that the listings navbar element exists
      webDriver.findElement(By.id("listingsbutton")).click()
      //Asserts that the listings button takes you to the Listings page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/listing")

      //Asserts that the about navbar element exists
      webDriver.findElement(By.id("aboutbutton")).click()
      //Asserts that the about button takes you to the about page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")

      //Asserts that the deals navbar element exists
      webDriver.findElement(By.id("dealsbutton")).click()
      //Asserts that the deals button takes you to the deals page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/deals")

      //Asserts that the searchbar exists and can take text input
      webDriver.findElement(By.id("searchbar")).sendKeys("logan")
      //Asserts that the search button exists and is clickable
      webDriver.findElement(By.id("searchbutton")).click()
      //Asserts that the search result loads
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/search/logan")

      //Asserts that the home navbar element exists
      webDriver.findElement(By.id("homebtn")).click()
      //Asserts that the home button takes you to the Home page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/")
    }
  }
}
