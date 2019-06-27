name := """akka-http-hal"""
version := "1.2.2"
organization := "io.pileworx"
homepage := Some(url("https://pileworx.io"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/pileworx/akka-http-hal"),
    "git@github.com/pileworx/akka-http-hal.git"))
developers := List(
  Developer(
    "marcuslange",
    "Marcus Lange",
    "marcus.lange@gmail.com",
    url("https://github.com/marcuslange")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
scalaVersion := "2.12.8"
scalacOptions := Seq("-feature", "-deprecation", "-encoding", "utf8")
credentials += Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", sys.env.getOrElse("SONATYPE_USER", ""), sys.env.getOrElse("SONATYPE_PASSWORD", ""))

libraryDependencies ++= {
  val akkaV = "2.5.21"
  val akkaHttpV = "10.1.7"
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,

    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % "test",
    "org.scalatest" %% "scalatest" % "3.0.6" % "test"
  )
}
