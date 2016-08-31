akka-http-hal
=============

HAL Specification support library for akka-http

Licensed under the Apache 2 license

[![Build Status](https://travis-ci.org/marcuslange/akka-http-hal.svg?branch=master)](https://travis-ci.org/marcuslange/akka-http-hal)

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

Usage:

Create your marshaller:
```scala
trait FooProtocol extends DefaultJsonProtocol {
  implicit val fooFormat = jsonFormat3(fooDto)
}
```
Create a resource adapter:
```scala
trait FooAdapter extends FooProtocol {
  def fooLink(rel:String, id:String) = rel -> Link(href = s"/foos/$id")
  def foosLink(rel:String) = rel -> Link(href = "/foos")

  def newFoo(id:String): JsValue = {
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

  def toResources(fooss:Seq[FooDto]): JsValue = {
    ResourceBuilder(
      withEmbedded = Some(Map(
        "foos" -> contacts.map(f => toResource(f))
      )),
      withLinks = Some(Map(foosLink("self")))
    ).build
  }

  def toResource(foo:FooDto): JsValue = {
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

HttpRequest Support
-------------------

By default the HAL links will not include the host or port.

If you would like host, port, or path prefix included, provide the HttpRequest.

```scala
  def toResource(foo:FooDto, req:HttpRequest): JsValue = {
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
To expose HAL Browser from your API add the halBrowser route

```scala
val routes = otherRoutes ~ halBrowserRoutes
```

the browser will be available at /halbrowser

TODO
-----------
curies support.

find more contributors. (hint)
