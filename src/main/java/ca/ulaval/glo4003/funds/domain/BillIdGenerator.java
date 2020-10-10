package ca.ulaval.glo4003.funds.domain;

import java.util.UUID;

public class BillIdGenerator {
  public BillId generate() {
    return new BillId(UUID.randomUUID());
  }
}
