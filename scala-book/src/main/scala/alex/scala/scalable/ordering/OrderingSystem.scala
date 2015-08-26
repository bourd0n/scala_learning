package alex.scala.scalable.ordering

trait OrderingSystem {
  type O <: Order
  type I <: Inventory
  type S <: Shipping

  trait Order {
    def placeOrder(i: I): Unit
  }

  trait Inventory {
    def itemExists(order: O): Boolean
  }

  trait Shipping {
    def scheduleShipping(order: O): Long
  }

  trait Ordering {
    this: I with S =>
    def placeOrder(o: O): Option[Long] = {
      if (itemExists(o)) {
        o.placeOrder(this)
        Some(scheduleShipping(o))
      }
      else None
    }
  }
}