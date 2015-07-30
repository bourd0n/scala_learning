package alex.scala.func.implicits

import alex.scala.func.implicits.ImplicitConversation._

object TestImplicit {
  def main(args: Array[String]) {
    val ic = new ImplicitConversation
    ic.setStr("hello")
    val isMatch = ic.isMatch(str => str.startsWith("he"))
    println("IsMatch=" + isMatch)
    val isMatchImpl = ic.isMatch(str => str ~ "he")
    println("IsMatch2=" + isMatchImpl)
  }
}
