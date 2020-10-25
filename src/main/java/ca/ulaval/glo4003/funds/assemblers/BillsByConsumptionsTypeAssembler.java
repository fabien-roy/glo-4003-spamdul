package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionTypeException;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillsByConsumptionTypes;
import java.util.List;

public class BillsByConsumptionsTypeAssembler {
  public BillsByConsumptionTypes assemble(List<Bill> bills) {
    BillsByConsumptionTypes billsByConsumptionTypes = new BillsByConsumptionTypes();
    for (Bill bill : bills) {
      billsByConsumptionTypes.addBillWithConsumptionsType(
          (bill.getConsumptionType().orElseThrow(InvalidConsumptionTypeException::new)), bill);
    }

    return billsByConsumptionTypes;
  }
}
