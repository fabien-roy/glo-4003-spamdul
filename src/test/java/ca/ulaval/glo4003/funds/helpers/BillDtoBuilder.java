package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.funds.helpers.BillMother.*;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;

import ca.ulaval.glo4003.funds.services.dto.BillDto;

public class BillDtoBuilder {
  public String billId = createBillId().toString();
  public String billType = createBillType().toString();
  public String description = createDescription();
  public double amountDue = createMoney().toDouble();
  public double amountPaid = createMoney().toDouble();
  public String time = createDateTime().toString();

  public static BillDtoBuilder aBillDto() {
    return new BillDtoBuilder();
  }

  public BillDto build() {
    BillDto billDto = new BillDto();
    billDto.billId = billId;
    billDto.billType = billType;
    billDto.description = description;
    billDto.amountDue = amountDue;
    billDto.amountPaid = amountPaid;
    billDto.time = time;
    return billDto;
  }
}
