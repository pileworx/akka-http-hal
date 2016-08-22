package akka.http.rest.hal

import akka.http.scaladsl.model.HttpRequest
import spray.json._

trait HalProtocol extends DefaultJsonProtocol {
  implicit val linkFormat = jsonFormat8(Link)
}

case class ResourceBuilder(
  withData:Option[JsValue] = None,
  withLinks:Option[Map[String, Link]] = None,
  withEmbedded:Option[Map[String, Seq[JsValue]]] = None,
  withRequest:Option[HttpRequest] = None
) extends HalProtocol {

  def build: JsValue = withData match {
    case Some(data) => makeHal(data)
    case None => makeHal(JsObject())
  }

  private def makeHal(jsValue:JsValue):JsValue = jsValue match {
    case jsonObj:JsObject => addEmbedded(addLinks(jsonObj))
    case _ => jsValue
  }

  private def addLinks(jsObject:JsObject):JsObject = withLinks match {
    case Some(links) => JsObject(jsObject.fields + ("_links" -> links.map {
      case (key, value) => (key, value.copy(href = Href.make(withRequest)))
    }.toJson))
    case _ => jsObject
  }

  private def addEmbedded(jsObject:JsObject):JsObject = withEmbedded match {
    case Some(embedded) => JsObject(jsObject.fields + ("_embedded" -> embedded.toJson))
    case _ => jsObject
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