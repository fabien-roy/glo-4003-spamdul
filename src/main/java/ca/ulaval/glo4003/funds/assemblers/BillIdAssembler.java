package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.domain.BillId;
import java.util.UUID;

public class BillIdAssembler {

  public BillId assemble(String billId) {
    return new BillId(UUID.fromString(billId));
  }
}
