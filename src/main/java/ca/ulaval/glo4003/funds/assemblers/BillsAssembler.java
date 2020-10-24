package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.domain.Bill;
import java.util.List;
import java.util.stream.Collectors;

public class BillsAssembler {

  public List<BillDto> assemble(List<Bill> bills) {
    return bills.stream().map(this::assemble).collect(Collectors.toList());
  }

  public BillDto assemble(Bill bill) {
    BillDto billDto = new BillDto();
    billDto.billId = bill.getId().toString();
    billDto.billType = bill.getBillTypes().toString();
    billDto.description = bill.getDescription();
    billDto.amountDue = bill.getAmountDue().toString();
    billDto.amountPaid = bill.getAmountPaid().toString();
    billDto.time = bill.getCustomDateTime().toString();

    return billDto;
  }
}
