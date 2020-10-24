package ca.ulaval.glo4003.times.services;

import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;

public class TimeService {
  private TimeScheduler timeScheduler;
  private CarbonCreditService carbonCreditService;

  public TimeService(TimeScheduler timeScheduler, CarbonCreditService carbonCreditService) {
    this.timeScheduler = timeScheduler;
    this.carbonCreditService = carbonCreditService;
  }

  public void startTimeScheduler() {
    timeScheduler.start();
  }

  public void timeToExtract() {
    System.out.println("SALUT");
  }
}
