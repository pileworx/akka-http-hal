package io.pileworx.akka.http.rest.hal

import org.scalatest.{Matchers, WordSpec}
import spray.json._

trait FakeDataProtocol extends DefaultJsonProtocol {
  implicit val fakeDataFormat: RootJsonFormat[FakeData] = jsonFormat2(FakeData)
}

class HalSpec extends WordSpec with Matchers with FakeDataProtocol {

  val curieKey = "pw"
  val curieUrl = "http://pileworx.io/{rel}"
  val url = "http://www.test.com"
  val data: JsValue = FakeData("one","two").toJson
  val links:Map[String, LinkT] = Map(
    "self" -> Link(href = url),
    "parent" -> Link(href = url)
    )
  val linksNested:Map[String, LinkT] = Map(
    "link_nested" -> Links(Seq(Link(href = url, title = Some("one")),Link(href = url, title = Some("two"))))
  )
  val embedded: Map[String, Seq[JsValue]] = Map(
    "fakesOne" -> Seq(FakeData("one","two").toJson),
    "fakesTwo" -> Seq(FakeData("three","four").toJson)
  )

  "Resource Builder" should {

    "return a resource with a provided link" in {
      val result = ResourceBuilder(
        withLinks = Some(links)).build.toString

      result should include(url)
      result should include("self")
      result should include("parent")
      result should include("_links")
    }

    "return a resource with a provided nested links" in {
      val result = ResourceBuilder(
        withLinks = Some(linksNested)).build.toString

      result should include(url)
      result should include("one")
      result should include("two")
      result should include("_links")
    }

    "return a resource with a provided embedded" in {
      val result = ResourceBuilder(
        withEmbedded = Some(embedded)).build.toString

      result should include("fakesOne")
      result should include("fakesTwo")
      result should include("one")
      result should include("two")
      result should include("three")
      result should include("four")
      result should include("_embedded")
    }

    "return a resource with the provided data" in {
      val result = ResourceBuilder(
        withData = Some(data)).build.toString

      result should include("one")
      result should include("two")
    }

    "return a resource with the _links property if no links are provided" in {
      val result = ResourceBuilder(
        withData = Some(data)).build.toString

      result should not include "_links"
    }

    "return a resource with the _embedded property if no embedded objects are provided" in {
      val result = ResourceBuilder(
        withData = Some(data)).build.toString

      result should not include "_embedded"
    }

    "return a resource without optional _link properties if unused" in {
      val result = ResourceBuilder(
        withLinks = links(Link(href = url))).build.toString

      result should not include "templated"
      result should not include "type"
      result should not include "deprecation"
      result should not include "name"
      result should not include "profile"
      result should not include "title"
      result should not include "hreflang"
    }

    "return a resource with optional _link property templated if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          templated = Some(true)
        ))).build.toString

      result should include("templated")
    }

    "return a resource with optional _link property type if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          `type` = Some("mything")
        ))).build.toString

      result should include("type")
    }

    "return a resource with optional _link property deprecation if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          deprecation = Some(true)
        ))).build.toString

      result should include("deprecation")
    }

    "return a resource with optional _link property name if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          name = Some("thisisme")
        ))).build.toString

      result should include("name")
    }

    "return a resource with optional _link property profile if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          profile = Some("alps")
        ))).build.toString

      result should include("profile")
    }

    "return a resource with optional _link property title if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          title = Some("my thing")
        ))).build.toString

      result should include("title")
    }

    "return a resource with optional _link property hreflang if used" in {
      val result = ResourceBuilder(
        withLinks = links(Link(
          href = url,
          hreflang = Some("en-us")
        ))).build.toString

      result should include("hreflang")
    }

    "return curies in links if curies are provided" in {
      val result = ResourceBuilder(
        withCuries = Some(Seq(Curie(
          name = curieKey,
          href = curieUrl
        )))).build.toString

      result should include("_links")
      result should include("curies")
      result should include(curieKey)
      result should include(curieUrl)
    }

    "return curies in links if curies are provided globally" in {
      val gCurieKey = "ak"
      val gCurieUrl = "http://akka.io/{rel}"
      ResourceBuilder.curies(Seq(Curie(name = gCurieKey, href = gCurieUrl)))

      val result = ResourceBuilder().build.toString

      result should include("_links")
      result should include("curies")
      result should include(gCurieKey)
      result should include(gCurieUrl)
    }

    "return curies in links if curies are provided globally and locally" in {
      val gCurieKey = "ts"
      val gCurieUrl = "http://typesafe.io/{rel}"
      ResourceBuilder.curies(Seq(Curie(name = gCurieKey, href = gCurieUrl)))

      val result = ResourceBuilder(
        withCuries = Some(Seq(Curie(
          name = curieKey,
          href = curieUrl
      )))).build.toString

      result should include("_links")
      result should include("curies")
      result should include(gCurieKey)
      result should include(gCurieUrl)
      result should include(curieKey)
      result should include(curieUrl)
    }
  }

  def links(link:Link):Option[Map[String,Link]] = Some(Map("self" -> link))
  def linksWithCurie(link:Link):Option[Map[String,Link]] = Some(Map(curieKey + ":find" -> link))
}

case class FakeData(title:String, description:String)
