import sbt._


object ExampleBuild extends Build {
  val hello = TaskKey[Unit]("hello", "Prints 'hello world'")

  val helloTask: Setting[Task[Unit]] = hello := {
    println("Hello World [From task]")
  }

  val project = Project("wekanban", file(".")).settings(helloTask)
}