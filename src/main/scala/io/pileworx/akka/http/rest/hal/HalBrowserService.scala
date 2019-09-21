package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.server.directives.ContentTypeResolver.Default
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/** akka-http Route to expose a HAL Browser
  * Mix in this trait with ~ halBrowserRoutes to expose a HAL Browser at /halbrowser
  */
trait HalBrowserService {
  val halBrowser = "halbrowser"
  val halBrowserRoutes: Route =
  path(halBrowser) {
    getFromResource("dependencies/halbrowser/browser.html")
  } ~
  path(halBrowser / RemainingPath) { rpath =>
    getFromResource(s"dependencies/halbrowser/$rpath")
  }
}