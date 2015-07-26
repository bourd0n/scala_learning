import sbt._
import Keys._

object ExampleBuild {
  val hello = TaskKey[Unit]("hello", "Prints 'hello world'")

  val helloTask: Setting[Task[Unit]] = hello := {
    println("Hello World [From task]")
  }
}

object H2TaskManager {
  var process: Option[Process] = None
  lazy val H2 = config("h2") extend Compile

  val startH2 = TaskKey[Unit]("startH2", "Start h2 database")
  val startH2Task: Setting[Task[Unit]] = startH2 in H2 <<= (fullClasspath in Compile) map {
    cp => startDatabase {
      cp.map(_.data).map(_.getAbsolutePath).filter(_.contains("h2database"))
    }
  }

  def startDatabase(paths: Seq[String]) = {
    process match {
      case None =>
        val cp = paths.mkString(System.getProperty("path.separator"))
        val command = "java -cp " + cp + " org.h2.tools.Server"
        println("Starting Database with command: " + command)
        process = Some(Process(command).run())
        println("Database started!")
      case Some(_) => println("H2 database already started")
    }
  }

  val stopH2 = TaskKey[Unit]("stopH2", "Stop H2 database")
  val stopH2Task = stopH2 in H2 := {
    process match {
      case None => println("Database is not running")
      case Some(_) =>
        println("Stopping database...")
        process.foreach(_.destroy())
        process = None
        println("Database stopped")
    }
  }
}

object MainBuild extends Build {

  import ExampleBuild._
  import H2TaskManager._

  lazy val weKanbanProject = Project("wekanban", file(".")).settings(helloTask, startH2Task, stopH2Task)
}