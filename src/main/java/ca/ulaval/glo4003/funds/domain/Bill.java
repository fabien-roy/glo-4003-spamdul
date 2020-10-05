package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.filesystem.CSVBillingZoneHelper;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class Bill {
  CSVBillingZoneHelper csvBillingZoneHelper =
      new CSVBillingZoneHelper(); // TODO : Remove this atrocity from our beautiful domain

  private float amountDue; // TODO : Use Money value object

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
