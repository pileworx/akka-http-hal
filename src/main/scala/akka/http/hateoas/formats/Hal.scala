package akka.http.hateoas.formats

import akka.http.hateoas._
import spray.json._

class Hal extends HateoasFormat with HateoasProtocol {
  def build(resource:ResourceBuilder): JsValue = resource.withData match {
      case Some(data) => makeHal(data, resource)
      case None => makeHal(JsObject(), resource)
  }

  private def makeHal(jsValue:JsValue, resource:ResourceBuilder):JsValue = jsValue match {
    case jsonObj:JsObject => addEmbedded(addLinks(jsonObj, resource), resource)
    case _ => jsValue
  }

  private def addLinks(jsObject:JsObject, resource:ResourceBuilder):JsObject = resource.withLinks match {
    case Some(links) => JsObject(jsObject.fields + ("_links" -> links.map { case (key, value) => (key, value.toJson) }.toJson))
    case _ => jsObject
  }

  private def addEmbedded(jsObject:JsObject, resource:ResourceBuilder):JsObject = resource.withEmbedded match {
    case Some(embedded) => JsObject(jsObject.fields + ("_embedded" -> embedded.toJson))
    case _ => jsObject
  }
}