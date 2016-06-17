package akka.http.rest.hal

import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.testkit.ScalatestRouteTest

class HalBrowserServiceSpec extends WordSpec with Matchers with ScalatestRouteTest with HalBrowserService {

  "The HAL Browser service" should {

    "return the landing page from /halbrowser" in {
      Get("/halbrowser") ~> halBrowserRoutes ~> check {
        responseAs[String] should not be empty
      }
    }

    "return resources from correct dynamic subpath of /halbrowser/..." in {
      Get("/halbrowser/js/hal.js") ~> halBrowserRoutes ~> check {
        responseAs[String] should include("var HAL = {")
      }
    }
  }
}
