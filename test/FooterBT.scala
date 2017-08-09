import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.openqa.selenium.By
import play.api.test._
import play.api.test.Helpers._
import java.util.concurrent.TimeUnit

@RunWith(classOf[JUnitRunner])
class FooterBT extends Specification {

  "Application" should {

    "Have a functional Footer on each Page" in new WithBrowser {
      //Connects to webpage and ensures the title is "Home", comfirming /index has loaded
      browser.goTo("http://localhost:" + "9000")
      browser.title() must contain("Home")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/")

      //Asserts that the footer is present on the page
      assert(webDriver.findElements( By.id("footer")).size() != 0)
      //Check that contact us link works
      webDriver.findElement(By.id("footercontact")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/contactUs")
      browser.goTo("http://localhost:" + "9000")
      //Check that about us link works
      webDriver.findElement(By.id("footerabout")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")
      browser.goTo("http://localhost:" + "9000")
      //Check that about us link works
      webDriver.findElement(By.id("footerclass")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://www.bbfc.co.uk/what-classification")
      browser.goTo("http://localhost:" + "9000")
      //Check that twitter link works
      webDriver.findElement(By.id("footertwitter")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://twitter.com/QaCinemas")
      browser.goTo("http://localhost:" + "9000")
      //Check that facebook link works
      webDriver.findElement(By.id("footerfacebook")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.facebook.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000")
      //Check that instagram link works
      webDriver.findElement(By.id("footerinstagram")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.instagram.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000")

      //Connects to webpage, comfirming /listing has loaded
      browser.goTo("http://localhost:" + "9000" + "/listing")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/listing")

      //Asserts that the footer is present on the page
      assert(webDriver.findElements( By.id("footer")).size() != 0)
      //Check that contact us link works
      webDriver.findElement(By.id("footercontact")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/contactUs")
      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Check that about us link works
      webDriver.findElement(By.id("footerabout")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")
      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Check that about us link works
      webDriver.findElement(By.id("footerclass")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://www.bbfc.co.uk/what-classification")
      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Check that twitter link works
      webDriver.findElement(By.id("footertwitter")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://twitter.com/QaCinemas")
      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Check that facebook link works
      webDriver.findElement(By.id("footerfacebook")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.facebook.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/listing")
      //Check that instagram link works
      webDriver.findElement(By.id("footerinstagram")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.instagram.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/listing")

      //Connects to webpage, comfirming /about has loaded
      browser.goTo("http://localhost:" + "9000" + "/about")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")

      //Asserts that the footer is present on the page
      assert(webDriver.findElements( By.id("footer")).size() != 0)
      //Check that contact us link works
      webDriver.findElement(By.id("footercontact")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/contactUs")
      browser.goTo("http://localhost:" + "9000" + "/about")
      //Check that about us link works
      webDriver.findElement(By.id("footerabout")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")
      browser.goTo("http://localhost:" + "9000" + "/about")
      //Check that about us link works
      webDriver.findElement(By.id("footerclass")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://www.bbfc.co.uk/what-classification")
      browser.goTo("http://localhost:" + "9000" + "/about")
      //Check that twitter link works
      webDriver.findElement(By.id("footertwitter")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://twitter.com/QaCinemas")
      browser.goTo("http://localhost:" + "9000" + "/about")
      //Check that facebook link works
      webDriver.findElement(By.id("footerfacebook")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.facebook.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/about")
      //Check that instagram link works
      webDriver.findElement(By.id("footerinstagram")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.instagram.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/about")

      //Connects to webpage, comfirming /deals has loaded
      browser.goTo("http://localhost:" + "9000" + "/deals")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/deals")

      //Asserts that the footer is present on the page
      assert(webDriver.findElements( By.id("footer")).size() != 0)
      //Check that contact us link works
      webDriver.findElement(By.id("footercontact")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/contactUs")
      browser.goTo("http://localhost:" + "9000" + "/deals")
      //Check that about us link works
      webDriver.findElement(By.id("footerabout")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/about")
      browser.goTo("http://localhost:" + "9000" + "/deals")
      //Check that about us link works
      webDriver.findElement(By.id("footerclass")).click()
      browser.webDriver.getCurrentUrl mustEqual("http://www.bbfc.co.uk/what-classification")
      browser.goTo("http://localhost:" + "9000" + "/deals")
      //Check that twitter link works
      webDriver.findElement(By.id("footertwitter")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://twitter.com/QaCinemas")
      browser.goTo("http://localhost:" + "9000" + "/deals")
      //Check that facebook link works
      webDriver.findElement(By.id("footerfacebook")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.facebook.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/deals")
      //Check that instagram link works
      webDriver.findElement(By.id("footerinstagram")).click()
      browser.webDriver.getCurrentUrl mustEqual("https://www.instagram.com/qacinemas/")
      browser.goTo("http://localhost:" + "9000" + "/deals")

    }
  }
}
