package alex.scala.other

sealed abstract class Maybe[+A] {
  def isEmpty: Boolean

  def get: A

  def getOrElse[B >: A](default: B): B = {
    if (isEmpty) default else get
  }
}

final case class Just[A](value: A) extends Maybe[A] {
  def isEmpty = false

  def get: A = value
}

case object Nil extends Maybe[Nothing] {
  def isEmpty = true

  def get = throw new RuntimeException("Error get in Nil")
}
