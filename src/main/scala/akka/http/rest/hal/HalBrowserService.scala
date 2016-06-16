package akka.http.rest.hal

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives._
import ContentTypeResolver.Default

trait HalBrowserService {
  val browserRoutes = path("halbrowser") {
    getFromResource("dependencies/halbrowser/browser.html")
  } ~
  path("halbrowser" / RestPath) { rpath =>
    getFromResource(s"dependencies/halbrowser/$rpath")
  }
}