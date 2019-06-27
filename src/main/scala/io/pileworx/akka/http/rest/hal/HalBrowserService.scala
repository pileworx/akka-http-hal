package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives._
import ContentTypeResolver.Default
import akka.http.scaladsl.server.Route

/** akka-http Route to expose a HAL Browser
  * Mix in this trait with ~ halBrowserRoutes to expose a HAL Browser at /halbrowser
  */
trait HalBrowserService {
  val halBrowserRoutes: Route =
  path("halbrowser") {
    getFromResource("dependencies/halbrowser/browser.html")
  } ~
  path("halbrowser" / RemainingPath) { rpath =>
    getFromResource(s"dependencies/halbrowser/$rpath")
  }
}