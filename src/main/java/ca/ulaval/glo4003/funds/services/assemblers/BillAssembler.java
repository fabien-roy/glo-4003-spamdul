package ca.ulaval.glo4003.funds.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import java.util.List;
import java.util.stream.Collectors;

public class BillAssembler {

  public List<BillDto> assemble(List<Bill> bills) {
    return bills.stream().map(this::assemble).collect(Collectors.toList());
  }

  public BillDto assemble(Bill bill) {
    BillDto billDto = new BillDto();
    billDto.billId = bill.getId().toString();
    billDto.billType = bill.getBillType().toString();
    billDto.description = bill.getDescription();
    billDto.amountDue = bill.getAmountDue().toDouble();
    billDto.amountPaid = bill.getAmountPaid().toDouble();
    billDto.time = bill.getCustomDateTime().toString();

    return billDto;
  }
}
