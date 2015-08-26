package alex.scala.scalable.ordering

import alex.scala.scalable.ordering.BookOrderingSystem.BookOrder

class Test {
  def main(args: Array[String]) {
    BookOrderingSystem.BookOrdering.placeOrder(new BookOrder())
  }
}
