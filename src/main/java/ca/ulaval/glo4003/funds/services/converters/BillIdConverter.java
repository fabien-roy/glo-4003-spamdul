package ca.ulaval.glo4003.funds.services.converters;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import java.util.UUID;

public class BillIdConverter {

  public BillId convert(String billId) {
    try {
      return new BillId(UUID.fromString(billId));
    } catch (IllegalArgumentException | NullPointerException exception) {
      throw new BillNotFoundException();
    }
  }
}
