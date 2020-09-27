package ca.ulaval.glo4003.domain.bill;

import ca.ulaval.glo4003.domain.parking.ReceptionMethods;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class BillTest {
  private String zone = "Zone1";
  private String time = "1j/sem/session";
  Bill bill;

  @Before
  public void setup() {
    bill = new Bill();
  }

  @Test
  public void whenCalculatingTheZonePrice_thenAddThisPriceToMoneyToPay() {
    bill.calculateZonePriceWithCommunicationType(ReceptionMethods.POSTAL, zone, time);

    Truth.assertThat(bill.getMoneyToPay()).isGreaterThan(0f);
  }

  @Test
  public void whenAddingPriceForPostalCode_thenAddFiveToMoneyToPay() {
    bill.addPriceForCommunicationMethod(ReceptionMethods.POSTAL);

    Truth.assertThat(bill.getMoneyToPay()).isEqualTo(5f);
  }

  @Test
  public void whenAddingPriceForMail_thenAdd0ToMoneyToPay() {
    bill.addPriceForCommunicationMethod(ReceptionMethods.EMAIL);

    Truth.assertThat(bill.getMoneyToPay()).isEqualTo(0f);
  }
}
