package com.kanban.application.model

import org.squeryl.Schema

object KanbanSchema extends Schema {
  val stories = table[Story]("STORIES")
}
