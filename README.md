akka-http-hal
=============

HAL Specification support library for akka-http.

Licensed under the Apache 2 license.

[![Build Status](https://travis-ci.org/pileworx/akka-http-hal.svg?branch=master)](https://travis-ci.org/pileworx/akka-http-hal)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2b351c4ec64e441f8b1bbf6ea4db3492)](https://www.codacy.com/app/Pileworx/akka-http-hal?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=pileworx/akka-http-hal&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/2b351c4ec64e441f8b1bbf6ea4db3492)](https://www.codacy.com/app/Pileworx/akka-http-hal?utm_source=github.com&utm_medium=referral&utm_content=pileworx/akka-http-hal&utm_campaign=Badge_Coverage)

Getting Started
---------------
We are currently working to add the binary to Maven Central. Until then, add using sbt.

In your build.sbt add:
```scala
lazy val akkaHttpHal = ProjectRef(
  uri("https://github.com/marcuslange/akka-http-hal.git"),
  "akka-http-hal")

lazy val root = (project in file(".")).dependsOn(akkaHttpHal)
```

Usage
-----
Create your marshaller:
```scala
trait FooProtocol extends DefaultJsonProtocol {
  implicit val fooFormat = jsonFormat3(FooDto)
}
```
Create a resource adapter:
```scala
trait FooAdapter extends FooProtocol {
  def fooLink(rel: String, id: String) = rel -> Link(href = s"/foos/$id")
  def foosLink(rel: String) = rel -> Link(href = "/foos")

  def newResource(id: String): JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(
        fooLink("self", id),
        foosLink("parent")
      ))
    ).build
  }

  def notFoundResource: JsValue = {
    ResourceBuilder(
      withLinks = Some(Map(contactsLink("parent")))
    ).build
  }

  def toResources(foos: Seq[FooDto]): JsValue = {
    ResourceBuilder(
      withEmbedded = Some(Map(
        "foos" -> foos.map(f => toResource(f))
      )),
      withLinks = Some(Map(foosLink("self")))
    ).build
  }

  def toResource(foo: FooDto): JsValue = {
    ResourceBuilder(
      withData = Some(foo.toJson),
      withLinks = Some(Map(
        fooLink("self", foo.id),
        foosLink("parent")
      ))
    ).build
  }
```
Create your routes:
```scala
trait FooRestPort extends FooAdapter {

  val fooService = new FooService with FooComponent

  val fooRoutes = path("foos") {
    get {
      complete {
        fooService.getAll.map(f => toResources(f))
      }
    } ~
    post {
      entity(as[CreateFooCommand]) { newFoo =>
        complete {
          Created -> fooService.add(newFoo).map(id => newResource(id))
        }
      }
    }
  } ~
  pathPrefix("foos" / Segment) { id =>
    get {
      complete {
        fooService.getById(id).map {
          case Some(f) => Marshal(toResource(f)).to[HttpResponse]
          case _ => Marshal(NotFound -> notFoundResource).to[HttpResponse]
        }
      }
    }
  }
}
```

Curies Support
--------------

Curies are supported in two ways.

The first is per resource:
```scala
ResourceBuilder(
  withCuries = Some(Seq(
    Curie(name = "ts", href = "http://typesafe.com/{rel}")
))).build
```
The second, and most likely more common way, is to set them globally:
```scala
ResourceBuilder.curies(Seq(
  Curie(name = "ts", href = "http://typesafe.com/{rel}"),
  Curie(name = "akka", href = "http://akka.io/{rel}")
))
```
Note: If you mix global and resource based curies they will be combined. Currently we do not check for duplicate entries.

For the links pointing to a curie, just prefix the key with the curie name and colon (ex "ts:info"). If a colon is found in a key, we do not alter the href by adding X-Forwarded data or the request host/port.

Array of Links Support
----------------------
If you require an array of links:
```json
{
  "_links": {
    "multiple_links": [
      {
        "href": "http://www.test.com?foo=bar",
        "name": "one"
      },
      {
        "href": "http://www.test.com?bar=baz",
        "name": "two"
      }
    ]
  }
}
```
This can be achieved by using the Links class which accepts a Sequence of Link:

```scala
Map(
  "multiple_links" -> Links(Seq(
    Link(href = url, name = Some("one")),
    Link(href = url, name = Some("two"))
))
```

HttpRequest Support
-------------------

By default the HAL links will not include the host or port.

If you would like host, port, or path prefix included, provide the HttpRequest.

```scala
  def toResource(foo: FooDto, req: HttpRequest): JsValue = {
    ResourceBuilder(
      withRequest = req,
      withData = Some(foo.toJson),
      withLinks = Some(Map(
        fooLink("self", foo.id),
        foosLink("parent")
      ))
    ).build
  }
}
```

This will produce a link with either the current host's information OR construct the links based on
the X-Forwarded-Proto, X-Forwarded-Host, X-Forwarded-Port, and X-Forwarded-Prefix headers.

HAL Browser
-----------
To expose HAL Browser from your API add the halBrowser route.

```scala
val routes = otherRoutes ~ halBrowserRoutes
```

The browser will be available at /halbrowser.

TODO
-----------
Publish to maven central.  
Find more contributors (hint).
