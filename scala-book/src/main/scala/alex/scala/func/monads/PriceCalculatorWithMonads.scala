package alex.scala.func.monads

import alex.scala.func.monads.StateMonad.State

object PriceCalculatorWithMonads {
  def findBasePrice(ps: PriceState): Double = {
    val basePrice = Stubs.findTheBasePrice(ps.productId)
    basePrice
  }

  def applyStateSpecificDiscounts(ps: PriceState): Double = {
    val discount = Stubs.findStateSpecificDiscount(ps.productId, ps.stateCode)
    ps.price - discount
  }

  def applyProductSpecificDiscounts(ps: PriceState): Double = {
    val discount = Stubs.findProductSpecificDiscount(ps.productId)
    ps.price - discount
  }

  def applyTax(ps: PriceState): Double = {
    val tax = Stubs.calculateTax(ps.productId, ps.price)
    ps.price + tax
  }

  def calculatePrice(productId: String, stateCode: String): Double = {
    def modifyPriceState(f: PriceState => Double): State[PriceState, Unit] = State.modify[PriceState](s => s.copy(price = f(s)))

    val stateMonad: State[PriceState, Unit] = for {
      _ <- modifyPriceState(findBasePrice)
      _ <- modifyPriceState(applyStateSpecificDiscounts)
      _ <- modifyPriceState(applyProductSpecificDiscounts)
      _ <- modifyPriceState(applyTax)
    } yield ()

    val initialPriceState = PriceState(productId, stateCode, 0.0)
    val finalPriceState = stateMonad(initialPriceState)._1
    finalPriceState.price
  }

  def calculatePrice2(productId: String, stateCode: String): Double = {
    def modifyPriceState(f: PriceState => Double) = State.modify[PriceState](s => s.copy(price = f(s)))

    val stateMonad: State[PriceState, Unit] = modifyPriceState(findBasePrice) flatMap { (a: Unit) =>
      modifyPriceState(applyStateSpecificDiscounts) flatMap { b =>
        modifyPriceState(applyProductSpecificDiscounts) flatMap { c =>
          modifyPriceState(applyTax) map { d => () }
        }
      }
    }
    val initialPriceState = PriceState(productId, stateCode, 0.0)
    val finalPriceState = stateMonad.apply(initialPriceState)._1
    val finalPrice = finalPriceState.price
    finalPrice
  }
}
