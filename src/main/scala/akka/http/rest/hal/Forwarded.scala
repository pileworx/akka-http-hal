package akka.http.rest.hal

import akka.http.scaladsl.model.{HttpHeader, HttpRequest}
import akka.http.scaladsl.model.headers.RawHeader

object Forwarded {
  def makeUrl(req:HttpRequest):String = {
    val proto:HttpHeader = req.headers.collectFirst {
      case h:HttpHeader if h.name() == "X-Forwarded-Proto" => h
    }.getOrElse(RawHeader("",""))

    val host:HttpHeader = req.headers.collectFirst {
      case h:HttpHeader if h.name() == "X-Forwarded-Host" => h
    }.getOrElse(RawHeader("",""))

    val port:HttpHeader = req.headers.collectFirst {
      case h:HttpHeader if h.name() == "X-Forwarded-Port" => h
    }.getOrElse(RawHeader("",""))

    val prefix:HttpHeader = req.headers.collectFirst {
      case h:HttpHeader if h.name() == "X-Forwarded-Prefix" => h
    }.getOrElse(RawHeader("",""))

    s"${proto.value}://${host.value}:${port.value}/${prefix.value}"
  }
}