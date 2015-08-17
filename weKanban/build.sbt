name := "wekanban"

organization := "scalainaction"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += Resolver.mavenLocal

// disable using the Scala version in output paths and artifacts
crossPaths := false

val springVersion = "4.1.6.RELEASE"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-server" % "9.3.1.v20150714" % "container",
  //  "org.scala-tools.testing" % "specs" % "1.6.2" % "test",
  "javax.servlet" % "javax.servlet-api" % "3.1.0",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-aop" % springVersion,
  "org.springframework" % "spring-webmvc" % springVersion,
  "org.springframework" % "spring-web" % springVersion,
  "org.springframework" % "spring-context-support" % springVersion,
  "org.apache.velocity" % "velocity" % "1.7"
)

libraryDependencies += "com.h2database" % "h2" % "1.4.187"

libraryDependencies += "org.squeryl" %% "squeryl" % "0.9.5-7"

enablePlugins(JettyPlugin)
