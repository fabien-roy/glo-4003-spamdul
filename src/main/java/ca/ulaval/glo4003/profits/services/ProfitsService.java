package ca.ulaval.glo4003.profits.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillProfitsCalculator;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.domain.BillsByConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.queryparams.BillQueryParams;
import ca.ulaval.glo4003.funds.domain.queryparams.BillQueryParamsAssembler;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import ca.ulaval.glo4003.profits.assemblers.ProfitsByConsumptionTypeAssembler;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import ca.ulaval.glo4003.profits.domain.ProfitsByConsumptionTypeFactory;
import java.util.List;

public class ProfitsService {

  private ProfitsAssembler profitsAssembler;
  private BillService billService;
  private BillQueryParamsAssembler billQueryParamsAssembler;
  private BillProfitsCalculator billProfitsCalculator;
  private ProfitsByConsumptionTypeFactory profitsByConsumptionTypeFactory;
  private ProfitsByConsumptionTypeAssembler profitsByConsumptionTypeAssembler;

  public ProfitsService(
      ProfitsAssembler profitsAssembler,
      BillService billService,
      BillQueryParamsAssembler billQueryParamsAssembler,
      BillProfitsCalculator billProfitsCalculator,
      ProfitsByConsumptionTypeFactory profitsByConsumptionTypeFactory,
      ProfitsByConsumptionTypeAssembler profitsByConsumptionTypeAssembler) {
    this.profitsAssembler = profitsAssembler;
    this.billService = billService;
    this.billQueryParamsAssembler = billQueryParamsAssembler;
    this.billProfitsCalculator = billProfitsCalculator;
    this.profitsByConsumptionTypeFactory = profitsByConsumptionTypeFactory;
    this.profitsByConsumptionTypeAssembler = profitsByConsumptionTypeAssembler;
  }

  public ProfitsDto getParkingStickerProfits(int year) {
    BillQueryParams billQueryParams =
        billQueryParamsAssembler.assemble(year, BillType.PARKING_STICKER);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculateTotalPrice(bills);
    return profitsAssembler.assemble(profits);
  }

  public ProfitsDto getAccessPassProfits(int year) {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assemble(year, BillType.ACCESS_PASS);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculateTotalPrice(bills);
    return profitsAssembler.assemble(profits);
  }

  public List<ProfitsByConsumptionTypeDto> getAccessPassProfitsByConsumptionType(int year) {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assemble(year, BillType.ACCESS_PASS);

    BillsByConsumptionTypes billsByConsumptionTypes =
        billService.getBillsByConsumptionsType(billQueryParams);

    List<ProfitByConsumptionType> profitByConsumptionTypes =
        profitsByConsumptionTypeFactory.create(billsByConsumptionTypes);
    return profitsByConsumptionTypeAssembler.assembleMany(profitByConsumptionTypes);
  }

  public ProfitsDto getOffenseProfits(int year) {
    BillQueryParams billQueryParams = billQueryParamsAssembler.assemble(year, BillType.OFFENSE);

    List<Bill> bills = billService.getAllBillsByQueryParams(billQueryParams);
    Money profits = billProfitsCalculator.calculatePaidPrice(bills);
    return profitsAssembler.assemble(profits);
  }
}
