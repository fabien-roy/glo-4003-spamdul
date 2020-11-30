package ca.ulaval.glo4003.funds.services.assemblers;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import java.util.UUID;

public class BillIdAssembler {

  public BillId assemble(String billId) {
    try {
      return new BillId(UUID.fromString(billId));
    } catch (IllegalArgumentException | NullPointerException exception) {
      throw new BillNotFoundException();
    }
  }
}
