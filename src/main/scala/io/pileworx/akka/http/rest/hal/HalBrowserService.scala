package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives._
import ContentTypeResolver.Default
import akka.http.scaladsl.server.Route

trait HalBrowserService {
  val halBrowserRoutes: Route =
  path("halbrowser") {
    getFromResource("dependencies/halbrowser/browser.html")
  } ~
  path("halbrowser" / RemainingPath) { rpath =>
    getFromResource(s"dependencies/halbrowser/$rpath")
  }
}