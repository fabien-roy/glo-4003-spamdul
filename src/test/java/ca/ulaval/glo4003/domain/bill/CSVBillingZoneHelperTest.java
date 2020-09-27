package ca.ulaval.glo4003.domain.bill;

import ca.ulaval.glo4003.domain.bill.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidZoneException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class CSVBillingZoneHelperTest {
  private CSVBillingZoneHelper CSVBillingZoneHelper = new CSVBillingZoneHelper();
  private static final String defaultZone = "Zone1";
  private static final String defaultTime = "1j/sem/session";

  @Test
  public void whenBillingAZone_thenReturnTheGoodPrice() {
    float price = CSVBillingZoneHelper.getZonePrice(defaultZone, defaultTime);
    Truth.assertThat(price).isEqualTo(163f);
  }

  @Test(expected = InvalidTimeException.class)
  public void whenBillingAZoneForAnInvalidTime_thenThrowInvalidTimeException() {
    CSVBillingZoneHelper.getZonePrice(defaultZone, "invalid");
  }

  @Test(expected = InvalidZoneException.class)
  public void whenBillingAInvalidZone_thenThrowInvalidZoneException() {
    CSVBillingZoneHelper.getZonePrice("invalid", defaultTime);
  }
}
