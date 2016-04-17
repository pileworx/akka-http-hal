package akka.http.hateoas

import akka.http.hateoas.formats.Hal
import spray.json.{DefaultJsonProtocol, JsValue}

trait HateoasFormat {
  def build(resource:ResourceBuilder):JsValue
}

trait HateoasProtocol extends DefaultJsonProtocol {
  implicit val linkFormat = jsonFormat8(Link)
}

case class ResourceBuilder(
  withData:Option[JsValue] = None,
  withLinks:Option[Map[String, Link]] = None,
  withEmbedded:Option[Map[String, Seq[JsValue]]] = None,
  withFormat:HateoasFormat = new Hal
) {
  def build() = {
    withFormat.build(this)
  }
}

case class Link(
  href:String,
  templated:Option[Boolean] = None,
  `type`:Option[String] = None,
  deprecation:Option[Boolean] = None,
  name:Option[String] = None,
  profile:Option[String] = None,
  title:Option[String] = None,
  hreflang:Option[String] = None
)