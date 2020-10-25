package ca.ulaval.glo4003.profits;

import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamsAssembler;
import ca.ulaval.glo4003.funds.services.BillProfitsCalculator;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.ProfitsResource;
import ca.ulaval.glo4003.profits.api.ProfitsResourceImplementation;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import ca.ulaval.glo4003.profits.assemblers.ProfitsByConsumptionTypeAssembler;
import ca.ulaval.glo4003.profits.domain.ProfitsByConsumptionTypeFactory;
import ca.ulaval.glo4003.profits.services.ProfitsService;

public class ProfitsInjector {

  public ProfitsResource createProfitsResource(BillService billService) {
    ProfitsService profitsService =
        new ProfitsService(
            new ProfitsAssembler(),
            billService,
            new BillQueryParamsAssembler(),
            new BillProfitsCalculator(),
            new ProfitsByConsumptionTypeFactory(new BillProfitsCalculator()),
            new ProfitsByConsumptionTypeAssembler());
    return new ProfitsResourceImplementation(profitsService);
  }
}
