package com.kanban.application.model

import java.sql.DriverManager

import org.squeryl.adapters.H2Adapter
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{Session, SessionFactory, Schema}

object KanbanSchema extends Schema {
  val stories = table[Story]("STORIES")

  def init() = {
    Class.forName("org.h2.Driver")
    if (SessionFactory.concreteFactory.isEmpty) {
      SessionFactory.concreteFactory = Some(() => Session.create(
        DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", ""), new H2Adapter))
    }
  }

  def main(args: Array[String]) {
    println("Initializing weKanban schema")
    init()
    inTransaction {
      drop
      create
    }
  }

  def tx[A](a: => A): A = {
    init()
    inTransaction(a)
  }

}
