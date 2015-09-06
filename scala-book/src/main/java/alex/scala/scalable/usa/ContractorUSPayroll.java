package alex.scala.scalable.usa;

import alex.scala.scalable.api.Contractor;
import alex.scala.scalable.api.ContractorPayroll;

import java.util.List;

public class ContractorUSPayroll extends USPayroll implements ContractorPayroll {
    @Override
    public String processContractors(List<Contractor> contractors) throws Throwable {
        return "Success";
    }
}
