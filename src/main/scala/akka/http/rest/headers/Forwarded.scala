package akka.http.rest.headers

import akka.http.scaladsl.model.headers.{ModeledCustomHeader, ModeledCustomHeaderCompanion}
import scala.util.Try

class ForwardedHost(host: String) extends ModeledCustomHeader[ForwardedHost] {
  def renderInRequests = false
  def renderInResponses = false
  val companion = ForwardedHost
  def value: String = host
}

object ForwardedHost extends ModeledCustomHeaderCompanion[ForwardedHost] {
  val name = "X-Forwarded-Host"
  def parse(value: String) = Try(new ForwardedHost(value))
}

class ForwardedPort(port: String) extends ModeledCustomHeader[ForwardedPort] {
  def renderInRequests = false
  def renderInResponses = false
  val companion = ForwardedPort
  def value: String = port
}

object ForwardedPort extends ModeledCustomHeaderCompanion[ForwardedPort] {
  val name = "X-Forwarded-Port"
  def parse(value: String) = Try(new ForwardedPort(value))
}

class ForwardedProto(protocol: String) extends ModeledCustomHeader[ForwardedProto] {
  def renderInRequests = false
  def renderInResponses = false
  val companion = ForwardedProto
  def value: String = protocol
}

object ForwardedProto extends ModeledCustomHeaderCompanion[ForwardedProto] {
  val name = "X-Forwarded-Proto"
  def parse(value: String) = Try(new ForwardedProto(value))
}

class ForwardedPrefix(prefix: String) extends ModeledCustomHeader[ForwardedPrefix] {
  def renderInRequests = false
  def renderInResponses = false
  val companion = ForwardedPrefix
  def value: String = prefix
}

object ForwardedPrefix extends ModeledCustomHeaderCompanion[ForwardedPrefix] {
  val name = "X-Forwarded-Prefix"
  def parse(value: String) = Try(new ForwardedPrefix(value))
}