package ca.ulaval.glo4003.carboncredits.systemtime;

import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import ca.ulaval.glo4003.interfaces.systemtime.JobHandler;

public class ConvertCarbonCreditHandler implements JobHandler {
  private final CarbonCreditService carbonCreditService;

  public ConvertCarbonCreditHandler(CarbonCreditService carbonCreditService) {
    this.carbonCreditService = carbonCreditService;
  }

  @Override
  public void invoke() {
    carbonCreditService.allocateRemainingFundToCarbonCreditInitiative();
  }
}
