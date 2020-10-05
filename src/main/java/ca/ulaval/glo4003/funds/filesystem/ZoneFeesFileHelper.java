package ca.ulaval.glo4003.funds.filesystem;

import ca.ulaval.glo4003.files.domain.StringMatrixFileHelper;
import ca.ulaval.glo4003.funds.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.funds.exceptions.InvalidZoneException;
import java.util.ArrayList;
import java.util.List;

public class ZoneFeesFileHelper {
  private static final String ZONE_FEES_PATH = "data/frais-zone.csv";

  private final StringMatrixFileHelper fileHelper;

  public ZoneFeesFileHelper(StringMatrixFileHelper fileHelper) {
    this.fileHelper = fileHelper;
  }

  public double getZonePrice(String zone, String time) {
    List<List<String>> csvData = fileHelper.readFile(ZONE_FEES_PATH);
    int columnNumber = findColumnNumberForZonePrice(time, csvData.get(0));
    return findPriceForSpecificColumnNumberAndZone(columnNumber, zone, csvData);
  }

  public List<String> getAllZones() {
    List<String> zones = new ArrayList<>();
    List<List<String>> csvData = fileHelper.readFile(ZONE_FEES_PATH);

    for (int i = 1; i < csvData.size(); i++) {
      zones.add(csvData.get(i).get(0));
    }

    return zones;
  }

  private double findPriceForSpecificColumnNumberAndZone(
      int columnNumber, String zone, List<List<String>> csvData) {
    for (int i = 1; i < csvData.size(); i++) {
      if (csvData.get(i).get(0).equals(zone)) {
        return Double.parseDouble(csvData.get(i).get(columnNumber));
      }
    }

    throw new InvalidZoneException();
  }

  private int findColumnNumberForZonePrice(String time, List<String> titleRow) {
    for (int i = 0; i < titleRow.size(); i++) {
      if (titleRow.get(i).equals(time)) {
        return i;
      }
    }

    throw new InvalidTimeException();
  }
}
