package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{HttpHeader, HttpRequest, Uri}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.Seq


class HrefSpec extends WordSpec with Matchers {
  "Href make with X-Forwarded headers" should {
    "return a url containing the http protocol and X-Forwarded-Host" in {
      val result = Href.make(forwardedWithHost)
      result should be(s"http://$xfHostName")
    }

    "return a url containing the X-Forwarded-Proto and X-Forwarded-Host" in {
      val result = Href.make(forwardedWithHostAndProto)
      result should be(s"$xfProtocol://$xfHostName")
    }

    "return a url containing the X-Forwarded-Host and X-Forwarded-Port" in {
      val result = Href.make(forwardedWithHostAndPort)
      result should be(s"http://$xfHostName:$xfPortNumber")
    }

    "return a url containing the X-Forwarded-Host and X-Forwarded-Prefix" in {
      val result = Href.make(forwardedWithHostAndPrefix)
      result should be(s"http://$xfHostName/$xfPathPrefix")
    }

    "return a url containing the X-Forwarded-Prefix and not contain a host if none is available" in {
      val result = Href.make(forwardedWithPrefix)
      result should be(s"/$xfPathPrefix")
    }

    "return a url containing the uri host and X-Forwarded-Port" in {
      val result = Href.make(requestWithUriAndForwardedPort)
      result should be(s"$uriProto://$uriHost:$xfPortNumber")
    }

    "return a url containing the uri host and X-Forwarded-Port without a second port" in {
      val result = Href.make(forwardedWithHostPortAndPort)
      result should be(s"$uriProto://$xfHostName:$xfPortNumber")
    }
  }

  "Href make with no request" should {
    "return as empty string" in {
      val result = Href.make(None)
      result shouldBe empty
    }
  }

  "Href make Uri in request" should {
    "return a url containing the protocol, host, and port" in {
      val result = Href.make(requestWithUri)
      result should be(s"$uriProto://$uriHost:$uriPort")
    }
  }

  private def requestWithUriAndForwardedPort = {
    Some(HttpRequest.apply(uri = uri, headers = Seq(xfPort)))
  }

  private def requestWithUri = Some(HttpRequest.apply(uri = uri))

  private def forwardedWithHost = {
    val headers: Seq[HttpHeader] = Seq(xfHost)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private def forwardedWithHostAndProto = {
    val headers: Seq[HttpHeader] = Seq(xfHost, xfProto)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private def forwardedWithHostAndPort = {
    val headers: Seq[HttpHeader] = Seq(xfHost, xfPort)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private def forwardedWithHostPortAndPort = {
    val headers: Seq[HttpHeader] = Seq(xfHostWithPort, xfPort)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private def forwardedWithHostAndPrefix = {
    val headers: Seq[HttpHeader] = Seq(xfHost, xfPrefix)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private def forwardedWithPrefix = {
    val headers: Seq[HttpHeader] = Seq(xfPrefix)
    Some(HttpRequest.apply(uri = Uri./, headers = headers))
  }

  private val xfHostName = "www.myhost.com"
  private val xfHost = RawHeader("X-Forwarded-Host", xfHostName)

  private val xfHostNameWithPort = "www.myhost.com:7900"
  private val xfHostWithPort = RawHeader("X-Forwarded-Host", xfHostNameWithPort)

  private val xfPortNumber = "9000"
  private val xfPort = RawHeader("X-Forwarded-Port", xfPortNumber)

  private val xfProtocol = "https"
  private val xfProto = RawHeader("X-Forwarded-Proto", xfProtocol)

  private val xfPathPrefix = "my/prefix"
  private val xfPrefix = RawHeader("X-Forwarded-Prefix", xfPathPrefix)

  private val uriHost = "api.myhost.com"
  private val uriPort = "9001"
  private val uriProto = "http"
  private val uriPrefix = "prefix"
  private val uri = Uri(s"$uriProto://$uriHost:$uriPort/$uriPrefix")
}
