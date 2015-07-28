name := "wekanban"

organization := "scalainaction"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += Resolver.mavenLocal

// disable using the Scala version in output paths and artifacts
crossPaths := false

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-server" % "9.3.1.v20150714" % "container",
//  "org.scala-tools.testing" % "specs" % "1.6.2" % "test",
  "javax.servlet" % "javax.servlet-api" % "3.1.0",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
)

libraryDependencies += "com.h2database" % "h2" % "1.4.187"

libraryDependencies += "org.squeryl" %% "squeryl" % "0.9.5-7"

enablePlugins(JettyPlugin)
