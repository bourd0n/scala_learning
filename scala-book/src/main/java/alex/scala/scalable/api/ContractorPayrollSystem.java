package alex.scala.scalable.api;

import java.util.List;

public interface ContractorPayrollSystem<T extends ContractorPayroll> extends PayrollSystem<T> {
    String proccessContractors(List<Contractor> contractors) throws Throwable;
}
