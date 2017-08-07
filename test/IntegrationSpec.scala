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

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + "9000")
      println(browser.webDriver.getPageSource)

      browser.title() must contain("Home")
      println(browser.webDriver.getCurrentUrl)
      val homebutton = webDriver.findElement(By.id("homebtn"))
      homebutton.click()
      println(browser.webDriver.getCurrentUrl)
      val listingbutton = webDriver.findElement(By.id("listingsbutton"))
      listingbutton.click()
      println(browser.webDriver.getCurrentUrl)
      val aboutusbutton = webDriver.findElement(By.id("aboutbutton"))
      aboutusbutton.click()
      println(browser.webDriver.getCurrentUrl)
      val dealsbutton = webDriver.findElement(By.id("dealsbutton"))
      dealsbutton.click()
      println(browser.webDriver.getCurrentUrl)
      webDriver.findElement(By.id("searchbar")).sendKeys("logan")
      val searchbutton = webDriver.findElement(By.id("searchbutton"))
      searchbutton.click()
      println(browser.webDriver.getCurrentUrl)
    }
  }
}
