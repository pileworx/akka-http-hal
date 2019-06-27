publishMavenStyle := true

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

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