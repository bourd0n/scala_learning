package alex.scala.scalable.payroll

object USPayrollInstance extends USPayrollSystem {
  override type P = USPayroll

  override def processPayroll(p: USPayroll): Either[String, Throwable] = p.processEmployees(Vector())
}
