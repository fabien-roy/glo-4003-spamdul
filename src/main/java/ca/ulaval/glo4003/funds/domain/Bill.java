package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.files.filesystem.CsvHelper;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class Bill {
  // TODO : Remove this atrocity from our beautiful domain
  ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(new CsvHelper());

  private float amountDue; // TODO : Use Money value object

  public Bill() {
    amountDue = 0;
  }

  public void calculateZonePriceWithCommunicationType(
      ReceptionMethods receptionMethods, String zone, String time) {
    amountDue += zoneFeesFileHelper.getZonePrice(zone, time);
    addPriceForCommunicationMethod(receptionMethods);
  }

  public void addPriceForCommunicationMethod(ReceptionMethods receptionMethod) {
    if (receptionMethod.equals(ReceptionMethods.POSTAL)) {
      amountDue += 5;
    }
  }

  public void setCsvBillingZoneHelper(ZoneFeesFileHelper zoneFeesFileHelper) {
    this.zoneFeesFileHelper = zoneFeesFileHelper;
  }

  public float getAmountDue() {
    return amountDue;
  }
}
