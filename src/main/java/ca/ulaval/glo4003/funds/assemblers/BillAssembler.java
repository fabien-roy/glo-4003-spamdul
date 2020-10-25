package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.domain.Bill;
import java.util.List;
import java.util.stream.Collectors;

public class BillAssembler {

  public List<BillDto> assemble(List<Bill> bills) {
    return bills.stream().map(this::assemble).collect(Collectors.toList());
  }

  public BillDto assemble(Bill bill) {
    BillDto billDto = new BillDto();
    billDto.billId = bill.getId().toString();
    billDto.billType = bill.getBillTypes().toString();
    billDto.description = bill.getDescription();
    billDto.amountDue = bill.getAmountDue().toDouble();
    billDto.amountPaid = bill.getAmountPaid().toDouble();
    billDto.time = bill.getCustomDateTime().toString();
    ConsumptionType consumptionType = bill.getConsumptionType().orElse(null);
    if (consumptionType != null) {
      billDto.consumptionType = consumptionType.toString();
    }

    return billDto;
  }
}
