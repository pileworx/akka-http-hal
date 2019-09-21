package io.pileworx.akka.http.rest.hal

import akka.http.scaladsl.model.HttpRequest
import spray.json._

/** Spray JSON support for serializing Link and Curie instances.
  */
trait HalProtocol extends DefaultJsonProtocol {
  implicit val linkFormat: RootJsonFormat[Link] = jsonFormat8(Link)
  implicit val curieFormat: RootJsonFormat[Curie] = jsonFormat3(Curie)
}

/** Global Curies storage.
  */
object ResourceBuilder {
  protected var globalCuries: Option[Seq[Curie]] = None
  def curies(curies:Seq[Curie]): Unit = globalCuries = Some(curies)
}

/** ResourceBuilder for constructing HAL resources
  *
  *  @constructor Creates a new HAL resource using Builder style notation.
  *  @param withCuries Adds curies specific to this resource
  *  @param withData Adds the primary JSON properties and values
  *  @param withLinks Adds Links to _links property
  *  @param withEmbedded Adds Embedded resources to _embedded
  *  @param withRequest Enables X-Forwarded headers support for proxied requests
  */
case class ResourceBuilder(
  withCuries:Option[Seq[Curie]] = None,
  withData:Option[JsValue] = None,
  withLinks:Option[Map[String, LinkT]] = None,
  withEmbedded:Option[Map[String, Seq[JsValue]]] = None,
  withRequest:Option[HttpRequest] = None
) extends HalProtocol {

  /** Builds the HAL Resource
    *
    * @return a JsValue representing the HAL resource.
    */
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

  private[this] def addEmbedded(jsObject:JsObject):JsObject = withEmbedded match {
    case Some(embedded) => JsObject(jsObject.fields + ("_embedded" -> embedded.toJson))
    case _ => jsObject
  }

  private def combinedLinks: Option[Map[String, Any]] = {
    val combinedLinks = links ++ combinedCuries
    if (combinedLinks.isEmpty) None else Some(combinedLinks)
  }

  private def links:Map[String, LinkT] = withLinks match {
    case Some(links) => links
    case _ => Map()
  }

  private def combinedCuries:Map[String, Seq[Curie]] = {
    val curies:Seq[Curie] = globalCuries ++ addCuries
    if (curies.isEmpty) Map() else Map(("curies", curies))
  }

  private def addCuries: Seq[Curie] = withCuries match {
    case Some(curies) => curies
    case _ => Seq[Curie]()
  }

  private def globalCuries: Seq[Curie] = ResourceBuilder.globalCuries match {
    case Some(curies) => curies
    case _ => Seq[Curie]()
  }

  private[this] def curied(key: String) = key.contains(":")

  private def curieHref(key: String, value: Link) = s"${if (!curied(key)) Href.make(withRequest)}${value.href}"
}

/** Base trait for Link or a Collection of related Links
  */
trait LinkT

/** HAL Link
  *
  * @see https://tools.ietf.org/html/draft-kelly-json-hal-08#section-5
  * @constructor Creates a HAL Link instance.
  * @param href URI to referenced resource.
  * @param templated Hint that the href is a Template URI.
  * @param type Hint for the media type of the referenced resource.
  * @param deprecation Hints that the Resource is deprecated. Value is a URI to more information on the deprecation.
  * @param name A secondary key for the resource.
  * @param profile URI to a profile of the resource conforming to https://tools.ietf.org/html/rfc6906.
  * @param title A string intended for labeling the resource.
  * @param hreflang A string representing the language of the resource.
  */
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

/** Links collection for nesting links.
  *
  *  @constructor creates a collection of Links.
  *  @param links Seq of Links to use in builder.
  */
case class Links(
  links:Seq[Link]
) extends LinkT

/** Curie support class
  *
  *  @constructor creates a curie instance
  *  @param name Name of the curie.
  *  @param href URI for curie.
  *  @param templated Hint that curie is a Template URI. Default is true.
  */
case class Curie(
  name:String,
  href:String,
  templated:Boolean = true
)
/** HAL specific exception
  *
  *  @constructor creates a curie instance
  *  @param msg Reason for failure.
  */
class HalException(msg: String) extends Exception(msg)