package com.kanban.application.model

import org.squeryl.PrimitiveTypeMode._

class ValidationException(message: String) extends RuntimeException(message)

class Story(val number: String, val title: String, val phase: String) {
  private[this] def validate() = {
    if ((number || title).isEmpty) {
      throw new ValidationException("Both number and title are required")
    }

    if (KanbanSchema.stories.where(a => a.number === number).nonEmpty) {
      throw new ValidationException("The story number is not unique")
    }
  }

  implicit class StringToTuple(val left: String) extends AnyVal {
    def ||(right: String): MyTuple2 = MyTuple2(left, right)
  }

}

//companion object
object MyTuple2 {
  def apply(s1: String, s2: String) = new MyTuple2(s1, s2)
}

class MyTuple2(val s1: String, val s2: String) extends Tuple2[String, String](s1, s2) {
  def isEmpty: Boolean = s1.isEmpty || s2.isEmpty
}
