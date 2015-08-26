package alex.scala.scalable.payroll

trait PayrollSystem {

  case class Employee(name: String, id: Long)

  type P <: Payroll

  trait Payroll {
    def processEmployees(employees: Vector[Employee]): Either[String, Throwable]
  }

  def processPayroll(p: P): Either[String, Throwable]
}
