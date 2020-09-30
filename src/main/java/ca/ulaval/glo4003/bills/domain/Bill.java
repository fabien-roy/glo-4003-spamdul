package ca.ulaval.glo4003.bills.domain;

import ca.ulaval.glo4003.bills.filesystem.CSVBillingZoneHelper;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class Bill {
  CSVBillingZoneHelper csvBillingZoneHelper = new CSVBillingZoneHelper();

  private float amountDue;

  public Bill() {
    amountDue = 0;
  }

  public void calculateZonePriceWithCommunicationType(
      ReceptionMethods receptionMethods, String zone, String time) {
    amountDue += csvBillingZoneHelper.getZonePrice(zone, time);
    addPriceForCommunicationMethod(receptionMethods);
  }

  public void addPriceForCommunicationMethod(ReceptionMethods receptionMethod) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL)) {
      amountDue += 5;
    }
  }

  public void setCsvBillingZoneHelper(CSVBillingZoneHelper csvBillingZoneHelper) {
    this.csvBillingZoneHelper = csvBillingZoneHelper;
  }

  public float getAmountDue() {
    return amountDue;
  }
}
