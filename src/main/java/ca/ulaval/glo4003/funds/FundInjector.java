package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramAllocationCalculator;
import ca.ulaval.glo4003.funds.infrastructure.BillRepositoryInMemory;
import ca.ulaval.glo4003.funds.infrastructure.SustainableMobilityProgramBankRepositoryInMemory;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.reports.services.ReportEventService;

public class FundInjector {

  private final BillRepository billRepository = new BillRepositoryInMemory();
  private final BillIdGenerator billIdGenerator = new BillIdGenerator();
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository =
      new SustainableMobilityProgramBankRepositoryInMemory();
  private final SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator =
          new SustainableMobilityProgramAllocationCalculator();

  public BillService createBillService(ReportEventService reportEventService) {
    BillFactory billFactory = new BillFactory(billIdGenerator);

    return new BillService(
        billFactory,
        billRepository,
        new BillAssembler(),
        reportEventService,
        sustainableMobilityProgramBankRepository,
        sustainableMobilityProgramAllocationCalculator);
  }

  public MoneyConverter createMoneyAssembler() {
    return new MoneyConverter();
  }

  public SustainableMobilityProgramBankRepository getSustainableMobilityProgramBankRepository() {
    return sustainableMobilityProgramBankRepository;
  }
}
