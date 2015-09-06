package alex.scala.scalable.payroll

trait USContractorPayrollSystem extends USPayrollSystem with ContractorPayrollSystem {
  class USPayroll extends super.USPayroll with Payroll {
      override def processContractors(contractors: Vector[Contractor]): Either[String, Throwable] = Left("Success")
  }
}
