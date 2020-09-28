package ca.ulaval.glo4003.domain.bill;

import ca.ulaval.glo4003.domain.parking.ReceptionMethods;

public class Bill {
  CSVBillingZoneHelper csvBillingZoneHelper = new CSVBillingZoneHelper();

  private float moneyToPay;

  public Bill() {
    moneyToPay = 0;
  }

  public void calculateZonePriceWithCommunicationType(
      ReceptionMethods receptionMethods, String zone, String time) {
    moneyToPay += csvBillingZoneHelper.getZonePrice(zone, time);
    addPriceForCommunicationMethod(receptionMethods);
  }

  public void addPriceForCommunicationMethod(ReceptionMethods receptionMethod) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL)) {
      moneyToPay += 5;
    }
  }

  public void setCsvBillingZoneHelper(CSVBillingZoneHelper csvBillingZoneHelper) {
    this.csvBillingZoneHelper = csvBillingZoneHelper;
  }

  public float getAmountDue() {
    return moneyToPay;
  }
}
