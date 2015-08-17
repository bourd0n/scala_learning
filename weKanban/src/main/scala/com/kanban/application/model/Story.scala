package com.kanban.application.model

import com.kanban.application.model.KanbanSchema._
import org.squeryl.PrimitiveTypeMode._

class ValidationException(message: String) extends RuntimeException(message)

object Story {
  def apply(number: String, title: String, phase: String) = new Story(number, title, phase)

  def apply(number: String, title: String) = new Story(number, title, "ready")

  implicit class StringToTuple(val left: String) extends AnyVal {
    def ||(right: String): MyTuple2 = MyTuple2(left, right)
  }

  def findAllByPhase(phase: String): Iterable[Story] = tx {
    from(stories)(s => where(s.phase === phase) select s) map {s=>s}
  }
}

class Story(val number: String, val title: String, val phase: String) {

  import Story._

  private[this] def validate() = {
    if ((number || title).isEmpty) {
      throw new ValidationException("Both number and title are required")
    }

    if (stories.where(a => a.number === number).nonEmpty) {
      throw new ValidationException("The story number is not unique")
    }
  }

  def save(): Either[Throwable, Unit] = {
    try {
      tx {
        validate()
        stories.insert(this)
        //fail - investigate
        //stories.insertOrUpdate(this)
        Right(())
      }
    } catch {
      case exception: Throwable => Left(exception)
    }
  }
}

//companion object
object MyTuple2 {
  def apply(s1: String, s2: String) = new MyTuple2(s1, s2)
}

class MyTuple2(val s1: String, val s2: String) extends Tuple2[String, String](s1, s2) {
  def isEmpty: Boolean = s1.isEmpty || s2.isEmpty
}
