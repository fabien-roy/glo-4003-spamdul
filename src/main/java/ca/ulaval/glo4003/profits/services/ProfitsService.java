package ca.ulaval.glo4003.profits.services;

import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;

public class ProfitsService {

  private ProfitsAssembler profitsAssembler;
  private BillService billService;

  public ProfitsService(ProfitsAssembler profitsAssembler, BillService billService) {
    this.profitsAssembler = profitsAssembler;
    this.billService = billService;
  }

  public ProfitsDto getParkingStickerProfits(int year) {
    return profitsAssembler.assemble(billService.getProfitsFromParkingStickerBillsByYear(year));
  }

  public ProfitsDto getAccessPassProfits(int year) {
    return null;
  }

  public ProfitsByConsumptionTypeDto getAccessPassProfitsByConsumptionType(int year) {
    return null;
  }

  public ProfitsDto getOffenseProfits(int year) {
    return null;
  }
}
