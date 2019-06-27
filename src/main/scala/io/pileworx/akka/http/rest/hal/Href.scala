package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.model.{HttpHeader, HttpRequest}

/** Builds the href for links
  *
  * If the X-Forwarded headers are available it constructs the URI based on the headers
  * If the Request is available with out X-Forwarded the request URI parts are used.
  * If the Request is not available, no changes are made to the URI
  */
object Href {
  /** Makes the href URI if optional HTTP Request is available
    *
    * @param maybeRequest Optional HTTP Request
    * @return An adjusted URI
    */
  def make(maybeRequest:Option[HttpRequest]):String = maybeRequest match {
    case Some(req) => if (containsForwarded(req)) ForwardedBuilder(req).build else UrlBuilder(req).build
    case None => ""
  }

  private def containsForwarded(req:HttpRequest) = {
    req.headers.exists(xf => xf.name.contains("X-Forwarded"))
  }
}

/** Builder to construct URI from X-Forwarded headers
  *
  * @param req The current HTTP Request
  */
case class ForwardedBuilder(req:HttpRequest) {
  private val withProto:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Proto" => h.value
  }

  private val withHost:Option[String] = {
    val xForwarded = req.headers.collectFirst { case h:HttpHeader if h.name == "X-Forwarded-Host" => stripPort(h.value) }
    val hostHeader = if (req.uri.authority.host.address.length > 0) Some(req.uri.authority.host.address) else None
    if (xForwarded.isInstanceOf[Some[String]]) xForwarded else hostHeader
  }

  private val withPort:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Port" => h.value
  }

  private val withPrefix:Option[String] = req.headers.collectFirst {
    case h:HttpHeader if h.name == "X-Forwarded-Prefix" => h.value
  }

  /** Builds the URI
    *
    * @return The altered URI with X-Forwarded parts
    */
  def build: String = withProto match  {
    case Some(xfp) => addHost(s"$xfp://")
    case _ => withHost match {
      case Some(_) => addHost("http://")
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

  private def stripPort(hostname:String) = {
    if(hostname.contains(":")) hostname.split(":")(0) else hostname
  }
}

/** Builder to construct URI from HTTP Request URI parts
  *
  * @param req The current HTTP Request
  */
case class UrlBuilder(req:HttpRequest) {
  private val proto:String = req.uri.scheme
  private val host:String = req.uri.authority.host.address
  private val port:Int = req.uri.authority.port

  /** Builds the URI
    *
    * @return The altered URI with HTTP Request URI parts
    */
  def build = s"$proto://$host:$port"
}