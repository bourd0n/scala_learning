package alex.scala.scalable.payroll.typeclass

import scala.language.higherKinds

object PayrollSystemWithTypeClasses {

  case class Employee(name: String, id: Long)

  trait PayrollProcessor[C[_], A] {
    def processPayroll(payees: Seq[A]): Either[String, Throwable]
  }

  case class USPayroll[A](payees: Seq[A])(implicit payrollProcessor: PayrollProcessor[USPayroll, A]) {
    def processPayroll = payrollProcessor.processPayroll(payees)
  }

  case class CanadaPayroll[A](payees: Seq[A])(implicit payrollProcessor: PayrollProcessor[CanadaPayroll, A]) {
    def processPayroll = payrollProcessor.processPayroll(payees)
  }

}

object PayrollProcessors {

  import PayrollSystemWithTypeClasses._

  implicit object USPayrollProcessor extends PayrollProcessor[USPayroll, Employee] {
    override def processPayroll(payees: Seq[Employee]): Either[String, Throwable] = Left("Success")
  }

}

object RunPayroll {
  import PayrollSystemWithTypeClasses._
  import PayrollProcessors._

  def run() = {
    val r = USPayroll(Vector(Employee("a", 1))).processPayroll
    r
  }
}