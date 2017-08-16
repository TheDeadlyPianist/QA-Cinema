import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.openqa.selenium.By
import play.api.test._
import play.api.test.Helpers._
import java.util.concurrent.TimeUnit

@RunWith(classOf[JUnitRunner])
class MovieInfoPage extends Specification {

  "Application" should {

    "Testing the information on the movie info page" in new WithBrowser {
      //Connects to webpage and ensures the title is "Home", comfirming /index has loaded
      browser.goTo("http://localhost:" + "9000" + "/movieInfo?movieID=374720")
      browser.title() must contain("Movie Information")
      browser.webDriver.getCurrentUrl mustEqual("http://localhost:9000/movieInfo?movieID=374720")

      browser.webDriver.findElement(By.id("filmTitle")).getText === "Dunkirk"
      browser.webDriver.findElement(By.id("releaseDate")).getText === "Release Date: 2017-07-19"
      browser.webDriver.findElement(By.id("runtime")).getText === "Runtime: 107 Minutes"
      browser.webDriver.findElement(By.id("movieRating")).getText === "Movie Rating: 7.4"
      browser.webDriver.findElement(By.id("overview")).getText === "Overview: Miraculous evacuation of Allied soldiers from Belgium, Britain, Canada, and France, who were cut off and surrounded by the German army from the beaches and harbor of Dunkirk, France, between May 26 and June 04, 1940, during Battle of France in World War II."
      browser.webDriver.findElement(By.id("genres")).getText === "Genres: ActionDramaHistoryThrillerWar"
      browser.webDriver.findElement(By.id("productionCompanies")).getText === "Production Companies: Canal+Studio CanalWarner Bros.SyncopyRatPac-Dune EntertainmentKaap Holland Film"
      browser.webDriver.findElement(By.id("ageRating")).getText === "Age Rating:"

    }
  }
}

