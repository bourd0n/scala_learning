package alex.scala.scalable.ordering

object BookOrderingSystem extends OrderingSystem{
  override type O = BookOrder
  override type S = BookShipping
  override type I = BookInventory

  class BookOrder extends Order {
    override def placeOrder(i: BookInventory): Unit = ???
  }

  trait BookShipping extends Shipping {
    override def scheduleShipping(order: BookOrder): Long = ???
  }

  trait BookInventory extends Inventory {
    override def itemExists(order: BookOrder): Boolean = ???
  }

  object BookOrdering extends Ordering with BookInventory with BookShipping

}
