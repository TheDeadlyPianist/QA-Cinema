import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.openqa.selenium.By
import play.api.test._
import play.api.test.Helpers._
import java.util.concurrent.TimeUnit

@RunWith(classOf[JUnitRunner])
class ListingsBT extends Specification {

  "Application" should {

    "Have a fully functioning listings page" in new WithBrowser {

      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Asserts that the listings button takes you to the Listings page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/listing")
      //Ensures that the tab content loads dynamically
      webDriver.manage.timeouts.implicitlyWait(10, TimeUnit.SECONDS)
      webDriver.findElement(By.id("imgundefined")).click()
      val showingmovie = browser.webDriver.getCurrentUrl
      assert(browser.webDriver.getCurrentUrl.contains("http://localhost:9000/movieInfo?movieID="))

      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Asserts that the listings button takes you to the Listings page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/listing")
      //Asserts that the tabs work
      webDriver.findElement(By.id("rightTab")).click()
      webDriver.findElement(By.id("leftTab")).click()
      webDriver.findElement(By.id("rightTab")).click()

      //Ensures that the tab content loads dynamically
      webDriver.manage.timeouts.implicitlyWait(10, TimeUnit.SECONDS)
      //Asserts that the tabs have worked correctly, and that the first movie in the list is not the same
      webDriver.findElement(By.id("imgundefined")).click()
      //Confirms that the movie clicked on in upcoming is not the one in now showing
      assert(browser.webDriver.getCurrentUrl != showingmovie)

      //Asserts that the home navbar element exists
      webDriver.findElement(By.id("homebtn")).click()
      //Asserts that the home button takes you to the Home page
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/")
    }
  }
}
