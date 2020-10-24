package ca.ulaval.glo4003.profits.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.domain.BillsByConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParams;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamsAssembler;
import ca.ulaval.glo4003.funds.services.BillProfitsCalculator;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.assemblers.ProfitByConsumptionTypeAssembler;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionTypeFactory;
import java.util.List;

public class ProfitsService {

  private ProfitsAssembler profitsAssembler;
  private BillService billService;
  private BillQueryParamsAssembler billQueryParamsAssembler;
  private BillProfitsCalculator billProfitsCalculator;
  private ProfitByConsumptionTypeFactory profitByConsumptionTypeFactory;
  private ProfitByConsumptionTypeAssembler profitByConsumptionTypeAssembler;

  public ProfitsService(
      ProfitsAssembler profitsAssembler,
      BillService billService,
      BillQueryParamsAssembler billQueryParamsAssembler,
      BillProfitsCalculator billProfitsCalculator,
      ProfitByConsumptionTypeFactory profitByConsumptionTypeFactory,
      ProfitByConsumptionTypeAssembler profitByConsumptionTypeAssembler) {
    this.profitsAssembler = profitsAssembler;
    this.billService = billService;
    this.billQueryParamsAssembler = billQueryParamsAssembler;
    this.billProfitsCalculator = billProfitsCalculator;
    this.profitByConsumptionTypeFactory = profitByConsumptionTypeFactory;
    this.profitByConsumptionTypeAssembler = profitByConsumptionTypeAssembler;
  }

  public ProfitsDto getParkingStickerProfits(int year) {
    BillQueryParams billQueryParams =
        billQueryParamsAssembler.assembleWithYear(year, BillType.PARKING_STICKER);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculate(bills);
    return profitsAssembler.assemble(profits);
  }

  public ProfitsDto getAccessPassProfits(int year) {
    BillQueryParams billQueryParams =
        billQueryParamsAssembler.assembleWithYear(year, BillType.ACCESS_PASS);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculate(bills);
    return profitsAssembler.assemble(profits);
  }

  public List<ProfitsByConsumptionTypeDto> getAccessPassProfitsByConsumptionType(int year) {
    BillQueryParams billQueryParams =
        billQueryParamsAssembler.assembleWithYear(year, BillType.ACCESS_PASS);

    BillsByConsumptionTypes billsByConsumptionTypes =
        billService.getBillsByConsumptionsType(billQueryParams);

    List<ProfitByConsumptionType> profitByConsumptionTypes =
        profitByConsumptionTypeFactory.create(billsByConsumptionTypes);
    return profitByConsumptionTypeAssembler.assembleMany(profitByConsumptionTypes);
  }

  public ProfitsDto getOffenseProfits(int year) {
    BillQueryParams billQueryParams =
        billQueryParamsAssembler.assembleWithYear(year, BillType.OFFENSE);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculate(bills);
    return profitsAssembler.assemble(profits);
  }
}
