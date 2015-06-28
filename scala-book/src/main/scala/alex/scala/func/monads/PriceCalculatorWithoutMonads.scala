package alex.scala.func.monads

object PriceCalculatorWithoutMonads {
  def findBasePrice(productId: String, stateCode: String): PriceState = {
    val basePrice = Stubs.findTheBasePrice(productId)
    PriceState(productId, stateCode, basePrice)
  }

  def applyStateSpecificDiscounts(ps: PriceState): PriceState = {
    val discount = Stubs.findStateSpecificDiscount(ps.productId, ps.stateCode)
    ps.copy(price = ps.price - discount)
  }

  def applyProductSpecificDiscounts(ps : PriceState): PriceState = {
    val discount = Stubs.findProductSpecificDiscount(ps.productId)
    ps.copy(price = ps.price - discount)
  }

  def applyTax(ps: PriceState): PriceState = {
    val tax = Stubs.calculateTax(ps.productId, ps.price)
    ps.copy(price = ps.price + tax)
  }

  def calculatePrice(productId: String, stateCode: String): Double = {
    val a = findBasePrice(productId, stateCode)
    val b = applyStateSpecificDiscounts(a)
    val c = applyProductSpecificDiscounts(b)
    val d = applyTax(c)
    d.price
  }
}
