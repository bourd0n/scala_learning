package alex.scala.scalable.payroll

trait ContractorPayrollSystem extends PayrollSystem {
  type P <: Payroll

  case class Contractor(name: String)

  trait Payroll extends super.Payroll {
    def processContractors(contractors: Vector[Contractor]): Either[String, Throwable]
  }

}
