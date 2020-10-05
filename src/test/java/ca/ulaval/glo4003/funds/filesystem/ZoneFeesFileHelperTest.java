package ca.ulaval.glo4003.funds.filesystem;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.domain.StringMatrixFileHelper;
import ca.ulaval.glo4003.funds.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.funds.exceptions.InvalidZoneException;
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
public class ZoneFeesFileHelperTest {
  @Mock private StringMatrixFileHelper fileHelper;

  private static final String defaultZone = "Zone1";
  private static final String defaultTime = "1j/sem/session";
  private static final double price = 103;
  private static final String CSV_FILE = "data/frais-zone.csv";
  private final List<List<String>> csvFile = new ArrayList<>();

  private ZoneFeesFileHelper zoneFeesFileHelper;

  @Before
  public void setup() {
    zoneFeesFileHelper = new ZoneFeesFileHelper(fileHelper);

    csvFile.add(Arrays.asList("name", defaultTime));
    csvFile.add(Arrays.asList(defaultZone, String.valueOf(price)));

    when(fileHelper.readFile(CSV_FILE)).thenReturn(csvFile);
  }

  @Test
  public void whenBillingAZone_thenReturnTheGoodPrice() {
    double price = zoneFeesFileHelper.getZonePrice(defaultZone, defaultTime);
    Truth.assertThat(price).isEqualTo(103d);
  }

  @Test
  public void whenGettingAllZones_thenReturnAllZones() {
    List<String> zones = zoneFeesFileHelper.getAllZones();
    Truth.assertThat(zones).contains(defaultZone);
  }

  @Test(expected = InvalidTimeException.class)
  public void whenBillingAZoneForAnInvalidTime_thenThrowInvalidTimeException() {
    zoneFeesFileHelper.getZonePrice(defaultZone, "invalid");
  }

  @Test(expected = InvalidZoneException.class)
  public void whenBillingAInvalidZone_thenThrowInvalidZoneException() {
    zoneFeesFileHelper.getZonePrice("invalid", defaultTime);
  }
}
