package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.model.HttpRequest
import spray.json._

trait HalProtocol extends DefaultJsonProtocol {
  implicit val linkFormat: RootJsonFormat[Link] = jsonFormat8(Link)
  implicit val curieFormat: RootJsonFormat[Curie] = jsonFormat3(Curie)
}

object ResourceBuilder {
  protected var globalCuries: Option[Seq[Curie]] = None
  def curies(curies:Seq[Curie]): Unit = globalCuries = Some(curies)
}

case class ResourceBuilder(
  withCuries:Option[Seq[Curie]] = None,
  withData:Option[JsValue] = None,
  withLinks:Option[Map[String, LinkT]] = None,
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

  private def addLinks(jsObject:JsObject):JsObject = combinedLinks match {
    case Some(links) => JsObject(jsObject.fields + ("_links" -> links.map {
      case (key, value:Link) =>
        (key, value.copy(href = curieHref(key, value)).toJson)
      case(key, value:Links) =>
        val links:Seq[Link] = value.links.map(v => v.copy(href = curieHref(key, v)))
        (key , links.toJson)
      case (key, value:Seq[Curie]) => (key, value.toJson)
      case (_,_) => throw new HalException("Failed to create Links. Invalid key/value supplied.")
    }.toJson))
    case _ => jsObject
  }

  private def addEmbedded(jsObject:JsObject):JsObject = withEmbedded match {
    case Some(embedded) => JsObject(jsObject.fields + ("_embedded" -> embedded.toJson))
    case _ => jsObject
  }

  private def combinedLinks: Option[Map[String, Any]] = {
    val combinedLinks = getLinks ++ combinedCuries
    if (combinedLinks.isEmpty) None else Some(combinedLinks)
  }

  private def getLinks:Map[String, LinkT] = withLinks match {
    case Some(links) => links
    case _ => Map()
  }

  private def combinedCuries:Map[String, Seq[Curie]] = {
    val curies:Seq[Curie] = getGlobalCuries ++ getCuries
    if (curies.isEmpty) Map() else Map(("curies", curies))
  }

  private def getCuries: Seq[Curie] = withCuries match {
    case Some(curies) => curies
    case _ => Seq[Curie]()
  }

  private def getGlobalCuries: Seq[Curie] = ResourceBuilder.globalCuries match {
    case Some(curies) => curies
    case _ => Seq[Curie]()
  }

  private def curied(key: String) = key.contains(":")

  private def curieHref(key: String, value: Link) = s"${if (!curied(key)) Href.make(withRequest)}${value.href}"
}

trait LinkT

case class Link(
  href:String,
  templated:Option[Boolean] = None,
  `type`:Option[String] = None,
  deprecation:Option[Boolean] = None,
  name:Option[String] = None,
  profile:Option[String] = None,
  title:Option[String] = None,
  hreflang:Option[String] = None
) extends LinkT

case class Links(
  links:Seq[Link]
) extends LinkT

case class Curie(
  name:String,
  href:String,
  templated:Boolean = true
)

class HalException(msg: String) extends Exception(msg)