package ca.ulaval.glo4003.funds.domain;

import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.funds.filesystem.CSVBillingZoneHelper;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillTest {
  private static final String zone = "Zone1";
  private static final String time = "1j/sem/session";
  private Bill bill;
  @Mock private CSVBillingZoneHelper csvBillingZoneHelper;

  @Before
  public void setup() {
    bill = new Bill();
    bill.setCsvBillingZoneHelper(csvBillingZoneHelper);
  }

  @Test
  public void whenCalculatingTheZonePrice_thenAddThisPriceToMoneyToPay() {
    bill.calculateZonePriceWithCommunicationType(ReceptionMethods.POSTAL, zone, time);

    verify(csvBillingZoneHelper).getZonePrice(zone, time);
  }

  @Test
  public void whenAddingPriceForPostalCode_thenAddFiveToMoneyToPay() {
    bill.addPriceForCommunicationMethod(ReceptionMethods.POSTAL);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(5f);
  }

  @Test
  public void whenAddingPriceForMail_thenAdd0ToMoneyToPay() {
    bill.addPriceForCommunicationMethod(ReceptionMethods.EMAIL);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(0f);
  }
}
