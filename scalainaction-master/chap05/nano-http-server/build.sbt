name := "Nano Http server"

version := "1.0"

organization := "Scala in Action"
resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.11.6"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")


libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "3.3.1" % "test",
  "org.scalaz.stream" %% "scalaz-stream" % "0.7.1",
  "org.specs2" %% "specs2-core" % "3.6.1" % "test"
)
