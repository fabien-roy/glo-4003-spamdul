package ca.ulaval.glo4003.funds.filesystem;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import com.google.common.truth.Truth;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZoneFeesFileHelperTest {
  @Mock private StringMatrixFileReader fileReader;

  private static final String CSV_FILE = "data/frais-zone.csv";
  private static final String FIRST_PERIOD = "firstPeriod";
  private static final String SECOND_PERIOD = "secondPeriod";
  private static final String FIRST_ZONE = "firstZone";
  private static final String SECOND_ZONE = "secondZone";
  private static final Double FIRST_ZONE_FIRST_PERIOD_FEE = 1d;
  private static final Double FIRST_ZONE_SECOND_PERIOD_FEE = 2d;
  private static final Double SECOND_ZONE_FIRST_PERIOD_FEE = 3d;
  private static final Double SECOND_ZONE_SECOND_PERIOD_FEE = 4d;

  private ZoneFeesFileHelper zoneFeesFileHelper;

  @Before
  public void setUp() {
    List<List<String>> csvFile = new ArrayList<>();
    csvFile.add(Arrays.asList("name", FIRST_PERIOD, SECOND_PERIOD));
    csvFile.add(
        Arrays.asList(
            FIRST_ZONE,
            FIRST_ZONE_FIRST_PERIOD_FEE.toString(),
            FIRST_ZONE_SECOND_PERIOD_FEE.toString()));
    csvFile.add(
        Arrays.asList(
            SECOND_ZONE,
            SECOND_ZONE_FIRST_PERIOD_FEE.toString(),
            SECOND_ZONE_SECOND_PERIOD_FEE.toString()));

    when(fileReader.readFile(CSV_FILE)).thenReturn(csvFile);

    zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);
  }

  @Test
  public void whenGettingZonesAndFees_thenReturnZones() {
    Map<String, Map<String, Double>> zonesAndFees = zoneFeesFileHelper.getZonesAndFees();
    Set<String> zones = zonesAndFees.keySet();

    Truth.assertThat(zones).hasSize(2);
    Truth.assertThat(zones).contains(FIRST_ZONE);
    Truth.assertThat(zones).contains(SECOND_ZONE);
  }

  @Test
  public void whenGettingZonesAndFees_thenReturnPeriods() {
    Map<String, Map<String, Double>> zonesAndFees = zoneFeesFileHelper.getZonesAndFees();
    Set<String> periodsForFirstZone = zonesAndFees.get(FIRST_ZONE).keySet();
    Set<String> periodsForSecondZone = zonesAndFees.get(SECOND_ZONE).keySet();

    Truth.assertThat(periodsForFirstZone).hasSize(2);
    Truth.assertThat(periodsForFirstZone).contains(FIRST_PERIOD);
    Truth.assertThat(periodsForFirstZone).contains(SECOND_PERIOD);
    Truth.assertThat(periodsForSecondZone).hasSize(2);
    Truth.assertThat(periodsForSecondZone).contains(FIRST_PERIOD);
    Truth.assertThat(periodsForSecondZone).contains(SECOND_PERIOD);
  }

  @Test
  public void whenGettingZonesAndFees_thenReturnFeesForPeriods() {
    Map<String, Map<String, Double>> zonesAndFees = zoneFeesFileHelper.getZonesAndFees();
    Double firstZoneFirstPeriodFee = zonesAndFees.get(FIRST_ZONE).get(FIRST_PERIOD);
    Double firstZoneSecondPeriodFee = zonesAndFees.get(FIRST_ZONE).get(SECOND_PERIOD);
    Double secondZoneFirstPeriodFee = zonesAndFees.get(SECOND_ZONE).get(FIRST_PERIOD);
    Double secondZoneSecondPeriodFee = zonesAndFees.get(SECOND_ZONE).get(SECOND_PERIOD);

    Truth.assertThat(firstZoneFirstPeriodFee).isEqualTo(FIRST_ZONE_FIRST_PERIOD_FEE);
    Truth.assertThat(firstZoneSecondPeriodFee).isEqualTo(FIRST_ZONE_SECOND_PERIOD_FEE);
    Truth.assertThat(secondZoneFirstPeriodFee).isEqualTo(SECOND_ZONE_FIRST_PERIOD_FEE);
    Truth.assertThat(secondZoneSecondPeriodFee).isEqualTo(SECOND_ZONE_SECOND_PERIOD_FEE);
  }
}
