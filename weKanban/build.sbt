name := "wekanban"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers += "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-server" % "7.0.0.RC2",
  "org.scala-tools.testing" % "specs" % "1.6.2" % "test"
)