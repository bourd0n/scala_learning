package alex.scala.scalable.api;

import java.util.List;

public interface ContractorPayroll extends Payroll {

    String processContractors(List<Contractor> contractors) throws Throwable;

}
