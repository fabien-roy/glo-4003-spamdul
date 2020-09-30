package ca.ulaval.glo4003.bills.filesystem;

import ca.ulaval.glo4003.bills.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.bills.exceptions.InvalidZoneException;
import ca.ulaval.glo4003.domain.file.CsvHelper;
import java.util.ArrayList;
import java.util.List;

public class CSVBillingZoneHelper {
  private final String csvFraisZonePath =
      "data/frais-zone.csv"; // TODO : Make sure this works on all OS
  CsvHelper csvHelper = new CsvHelper();

  public void setCsvHelper(CsvHelper csvHelper) {
    this.csvHelper = csvHelper;
  }

  public float getZonePrice(String zone, String time) {
    List<List<String>> csvData = csvHelper.getCsvFileInJavaFormat(csvFraisZonePath);
    int columnNumber = findColumnNumberForZonePrice(time, csvData.get(0));
    return findPriceForSpecificColumnNumberAndZone(columnNumber, zone, csvData);
  }

  public List<String> getAllZones() {
    List<String> zones = new ArrayList<>();
    List<List<String>> csvData = csvHelper.getCsvFileInJavaFormat(csvFraisZonePath);

    for (int i = 1; i < csvData.size(); i++) {
      zones.add(csvData.get(i).get(0));
    }

    return zones;
  }

  private float findPriceForSpecificColumnNumberAndZone(
      int columnNumber, String zone, List<List<String>> csvData) {
    for (int i = 1; i < csvData.size(); i++) {
      if (csvData.get(i).get(0).equals(zone)) {
        return Float.parseFloat(csvData.get(i).get(columnNumber));
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
