package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.BillFactory;
import ca.ulaval.glo4003.funds.domain.BillIdGenerator;
import ca.ulaval.glo4003.funds.domain.BillRepository;
import ca.ulaval.glo4003.funds.infrastructure.BillRepositoryInMemory;
import ca.ulaval.glo4003.funds.services.BillService;

public class FundInjector {

  private final BillRepository billRepository = new BillRepositoryInMemory();
  private final BillIdGenerator billIdGenerator = new BillIdGenerator();

  public BillService createBillService() {
    BillFactory billFactory = new BillFactory(billIdGenerator);
    return new BillService(billFactory, billRepository);
  }

  public MoneyAssembler createMoneyAssembler() {
    return new MoneyAssembler();
  }
}
