package ca.ulaval.glo4003.funds;

import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillsByConsumptionsTypeAssembler;
import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramAllocationCalculator;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamAssembler;
import ca.ulaval.glo4003.funds.domain.queries.BillTypeQueryParamAssembler;
import ca.ulaval.glo4003.funds.domain.queries.YearQueryParamAssembler;
import ca.ulaval.glo4003.funds.infrastructure.BillQueryBuilderInMemory;
import ca.ulaval.glo4003.funds.infrastructure.BillRepositoryInMemory;
import ca.ulaval.glo4003.funds.infrastructure.SustainableMobilityProgramBankRepositoryInMemory;
import ca.ulaval.glo4003.funds.services.BillService;
import java.util.HashSet;
import java.util.Set;

public class FundInjector {

  private final BillRepository billRepository = new BillRepositoryInMemory();
  private final BillIdGenerator billIdGenerator = new BillIdGenerator();
  private final BillQueryBuilderInMemory billQueryBuilderInMemory = new BillQueryBuilderInMemory();
  private final BillTypeQueryParamAssembler billTypeQueryParamAssembler =
      new BillTypeQueryParamAssembler();
  private final YearQueryParamAssembler yearQueryParamAssembler = new YearQueryParamAssembler();
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository =
      new SustainableMobilityProgramBankRepositoryInMemory();
  private final SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator =
          new SustainableMobilityProgramAllocationCalculator();

  public BillService createBillService() {
    Set<BillQueryParamAssembler> billQueryParamAssemblers = new HashSet<>();
    billQueryParamAssemblers.add(billTypeQueryParamAssembler);
    billQueryParamAssemblers.add(yearQueryParamAssembler);

    BillFactory billFactory = new BillFactory(billIdGenerator);
    BillQueryFactory billQueryFactory =
        new BillQueryFactory(billQueryBuilderInMemory, billQueryParamAssemblers);

    return new BillService(
        billFactory,
        billRepository,
        new BillAssembler(),
        billQueryFactory,
        sustainableMobilityProgramBankRepository,
        sustainableMobilityProgramAllocationCalculator,
        new BillsByConsumptionsTypeAssembler());
  }

  public MoneyAssembler createMoneyAssembler() {
    return new MoneyAssembler();
  }

  public SustainableMobilityProgramBankRepository getSustainableMobilityProgramBankRepository() {
    return sustainableMobilityProgramBankRepository;
  }
}
