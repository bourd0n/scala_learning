package application.model

import application.model.KanbanSchema._
import org.squeryl.PrimitiveTypeMode._

class ValidationException(message: String) extends RuntimeException(message)

object Story {
  def apply(number: String, title: String, phase: String) = new Story(number, title, phase)

  def apply(number: String, title: String) = new Story(number, title, "ready")

  implicit class StringToTuple(val left: String) extends AnyVal {
    def ||(right: String): MyTuple2 = MyTuple2(left, right)
  }

  def findAllByPhase(phase: String): Iterable[Story] = tx {
    from(stories)(s => where(s.phase === phase) select s) map { s => s }
  }

  def findByNumber(number: String) = tx {
    stories.where(s => s.number === number).single
  }
}

class Story(val number: String, val title: String, val phase: String) {

  import Story._

  //for velocity
  def getNumber = number

  def getTitle = title

  private def phaseLimits = Map("ready" -> Some(3), "dev" -> Some(2), "test" -> Some(2), "deploy" -> None)

  private[this] def validate() = {
    if ((number || title).isEmpty) {
      throw new ValidationException("Both number and title are required")
    }

    if (stories.where(a => a.number === number).nonEmpty) {
      throw new ValidationException("The story number is not unique")
    }
  }

  private[this] def validateLimits(phase: String) = {
    val currentSize: Long = from(stories) (s => where(s.phase === phase) compute count)
    if (currentSize == phaseLimits(phase).getOrElse(-1)) {
      throw new ValidationException("You cannot exceed the limit set for this phase")
    }
  }

  def moveTo(phase: String) : Either[Throwable, String] = {
    tx {
      try {
        validateLimits(phase)
        update(stories) (s => where(s.number === this.number) set(s.phase := phase))
        Right(s"Card ${this.number} is moved to $phase phase sucessfully.")
      } catch {
        case exception: Throwable => Left(exception)
      }
    }
  }

  def save(): Either[Throwable, Unit] = {
    try {
      tx {
        validate()
        stories.insert(this)
        //todo fail - investigate
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
