package akka.http.rest.hal

import akka.http.scaladsl.model.{HttpHeader, HttpRequest}

object Href {
  def make(maybeRequest:Option[HttpRequest]):String = maybeRequest match {
    case Some(req) => if (containsForwarded(req)) ForwardedBuilder(req).build else UrlBuilder().build
    case None => ""
  }

  def containsForwarded(req:HttpRequest) = {
    req.headers.exists(xf => xf.name.contains("X-Forwarded"))
  }
}

case class ForwardedBuilder(req:HttpRequest) {
  private val withProto:Option[HttpHeader] = req.headers.collectFirst {
    case h:HttpHeader if h.name() == "X-Forwarded-Proto" => h
  }
  private val withHost:Option[HttpHeader] = req.headers.collectFirst {
    case h:HttpHeader if h.name() == "X-Forwarded-Host" => h
  }

  private val withPort:Option[HttpHeader] = req.headers.collectFirst {
    case h:HttpHeader if h.name() == "X-Forwarded-Port" => h
  }

  private val withPrefix:Option[HttpHeader] = req.headers.collectFirst {
    case h:HttpHeader if h.name() == "X-Forwarded-Prefix" => h
  }

  def build = addProto

  private def addProto = withProto match  {
    case Some(xfp) => addHost(s"${xfp.value}://")
    case _ => addHost("")
  }

  private def addHost(protocol:String) = withHost match {
    case Some(xfh) => addPort(s"$protocol${xfh.value}")
    case _ => addPort(protocol)
  }

  private def addPort(host:String) = withPort match {
    case Some(xfp) => addPrefix(s"$host:${xfp.value}")
    case _ => addPrefix(host)
  }

  private def addPrefix(port:String) = withPrefix match {
    case Some(xfp) => s"$port/${xfp.value}"
    case _ => port
  }
}

case class UrlBuilder() {
  def build = ???
}


/**
def addUrl(req:HttpRequest):String = {
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
*/