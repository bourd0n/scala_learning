package alex.scala.scalable.usa;

import alex.scala.scalable.api.Employee;
import alex.scala.scalable.api.Payroll;

import java.util.List;

public class USPayroll implements Payroll {
    @Override
    public String processEmployees(List<Employee> employees) throws Throwable {
        return "Success";
    }
}
