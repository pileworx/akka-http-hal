package akka.http.rest.hal

import akka.http.scaladsl.model.{HttpHeader, HttpRequest}

object Href {
  def make(maybeRequest:Option[HttpRequest]):String = maybeRequest match {
    case Some(req) => if (containsForwarded(req)) ForwardedBuilder(req).build else UrlBuilder(req).build
    case None => ""
  }

  def containsForwarded(req:HttpRequest) = {
    req.headers.exists(xf => xf.name.contains("X-Forwarded"))
  }
}

case class ForwardedBuilder(req:HttpRequest) {
  private val withProto:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Proto" => h.value
  }

  private val withHost:Option[String] = {
    val xForwarded = req.headers.collectFirst { case h:HttpHeader if h.name == "X-Forwarded-Host" => h.value }
    val hostHeader = req.headers.collectFirst { case h:HttpHeader if h.name == "Host" => extractHost(h.value) }
    if (xForwarded.isInstanceOf[Some[String]]) xForwarded else hostHeader
  }

  private val withPort:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Port" => h.value
  }

  private val withPrefix:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Prefix" => h.value
  }

  def build = addProto

  private def addProto = withProto match  {
    case Some(xfp) => addHost(s"$xfp://")
    case _ => withHost match {
      case Some(h) => addHost("http://")
      case _ => addHost("")
    }
  }

  private def addHost(protocol:String) = withHost match {
    case Some(xfh) => addPort(s"$protocol$xfh")
    case _ => addPort(protocol)
  }

  private def addPort(host:String) = withPort match {
    case Some(xfp) => addPrefix(s"$host:$xfp")
    case _ => addPrefix(host)
  }

  private def addPrefix(port:String) = withPrefix match {
    case Some(xfp) => s"$port/$xfp"
    case _ => port
  }

  private def extractHost(hostWithPort:String):String = {
    "^.*(?=(\\:))".r findFirstMatchIn hostWithPort match {
      case Some(found) => found.toString
      case _ => hostWithPort
    }
  }
}

case class UrlBuilder(req:HttpRequest) {
  private val proto:String = req.protocol.value
  private val host:String = req.headers.collectFirst { case h:HttpHeader if h.name == "Host" => h.value}.getOrElse("")

  def build = s"${proto}://${host}"
}