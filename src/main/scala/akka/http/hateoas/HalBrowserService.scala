package akka.http.hateoas

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives._
import ContentTypeResolver.Default

trait HalBrowserService {
  val browserRoutes = path("halbrowser") {
    getFromResource("halbrowser/browser.html")
  } ~
  path("halbrowser" / RestPath) { rpath =>
    getFromResource(s"halbrowser/$rpath")
  }
}