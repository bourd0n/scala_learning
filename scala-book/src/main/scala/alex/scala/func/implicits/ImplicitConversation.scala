package alex.scala.func.implicits

object ImplicitConversation extends ImplicitConversation

class ImplicitConversation {
  private var strField: String = _

  def setStr(newStr: String) = strField = newStr
  def getStr = strField

  def isMatch(f: String => Boolean): Boolean = f(strField)

  implicit def toImplicitString(s: String): ImplicitString = new ImplicitString(s)
}

class ImplicitString(val s: String) {
  def ~(p: String): Boolean = s.startsWith(p)
}
