package alex.scala.scalable.payroll

trait USPayrollSystem extends PayrollSystem{
  class USPayroll extends Payroll {
    override def processEmployees(employees: Vector[Employee]): Either[String, Throwable] = Left("SUccess")
  }
}
