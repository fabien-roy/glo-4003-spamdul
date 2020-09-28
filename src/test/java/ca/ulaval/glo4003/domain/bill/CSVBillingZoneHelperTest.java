package ca.ulaval.glo4003.domain.bill;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.domain.bill.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.domain.bill.exceptions.InvalidZoneException;
import ca.ulaval.glo4003.domain.file.FileHelper;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVBillingZoneHelperTest {
  private CSVBillingZoneHelper csvBillingZoneHelper;
  private static final String defaultZone = "Zone1";
  private static final String defaultTime = "1j/sem/session";
  private static final float price = 103;
  private final String csvFraisZonePath =
      ".\\src\\main\\java\\ca\\ulaval\\glo4003\\document\\frais-zone.csv";
  private final List<List<String>> csvFile = new ArrayList<>();
  @Mock private FileHelper fileHelper;

  @Before
  public void setup() {
    csvBillingZoneHelper = new CSVBillingZoneHelper();
    csvBillingZoneHelper.setFileHelper(fileHelper);

    csvFile.add(Arrays.asList("name", defaultTime));
    csvFile.add(Arrays.asList(defaultZone, String.valueOf(price)));

    when(fileHelper.getCsvFileInJavaFormat(csvFraisZonePath)).thenReturn(csvFile);
  }

  @Test
  public void whenBillingAZone_thenReturnTheGoodPrice() {
    float price = csvBillingZoneHelper.getZonePrice(defaultZone, defaultTime);
    Truth.assertThat(price).isEqualTo(103f);
  }

  @Test
  public void whenGettingAllZones_thenReturnAllZones() {
    List<String> zones = csvBillingZoneHelper.getAllZones();
    Truth.assertThat(zones).contains(defaultZone);
  }

  @Test(expected = InvalidTimeException.class)
  public void whenBillingAZoneForAnInvalidTime_thenThrowInvalidTimeException() {
    csvBillingZoneHelper.getZonePrice(defaultZone, "invalid");
  }

  @Test(expected = InvalidZoneException.class)
  public void whenBillingAInvalidZone_thenThrowInvalidZoneException() {
    csvBillingZoneHelper.getZonePrice("invalid", defaultTime);
  }
}
