name := """akka-http-hal"""

version := "1.0.1"

scalaVersion := "2.12.2"

scalacOptions := Seq("-feature", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.5.2"
  val akkaHttpV = "10.0.7"
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,

    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}