package alex.scala.scalable.api;

public interface PayrollSystem<T extends Payroll> {

    String processPayroll(T payroll) throws Throwable;
    
}
