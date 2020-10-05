package ca.ulaval.glo4003.funds.filesystem;

import ca.ulaval.glo4003.files.domain.StringMatrixFileHelper;
import ca.ulaval.glo4003.funds.exceptions.InvalidTimeException;
import ca.ulaval.glo4003.funds.exceptions.InvalidZoneException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO : Refactor the tests for ZoneFeesFileHelper
public class ZoneFeesFileHelper {
  private static final String ZONE_FEES_PATH = "data/frais-zone.csv";

  private final List<List<String>> csvData;

  public ZoneFeesFileHelper(StringMatrixFileHelper fileHelper) {
    csvData = fileHelper.readFile(ZONE_FEES_PATH);
  }

  // TODO : Probably useless
  public double getZonePrice(String zone, String time) {
    int columnNumber = findColumnNumberForZonePrice(time, csvData.get(0));
    return findPriceForSpecificColumnNumberAndZone(columnNumber, zone, csvData);
  }

  // TODO : Test this?
  public Map<String, Map<String, Double>> getZonesAndFees() {
    List<String> zones = getAllZones();
    Map<String, Map<String, Double>> zonesAndFees = new HashMap<>();

    for (int i = 0; i < zones.size(); i++) {
      zonesAndFees.put(zones.get(i), getFeesForZoneIndex(i + 1));
    }

    return zonesAndFees;
  }

  private Map<String, Double> getFeesForZoneIndex(int zoneIndex) {
    List<String> parkingPeriods = getAllParkingPeriods();
    Map<String, Double> feesForZoneIndex = new HashMap<>();

    for (int i = 0; i < parkingPeriods.size(); i++) {
      Double feeForParkingPeriod = Double.parseDouble(csvData.get(zoneIndex).get(i + 1));
      feesForZoneIndex.put(parkingPeriods.get(i), feeForParkingPeriod);
    }

    return feesForZoneIndex;
  }

  // TODO : Should this be private?
  public List<String> getAllZones() {
    List<String> zones = new ArrayList<>();

    for (int i = 1; i < csvData.size(); i++) {
      zones.add(csvData.get(i).get(0));
    }

    return zones;
  }

  private List<String> getAllParkingPeriods() {
    List<String> parkingPeriods = new ArrayList<>();

    for (int i = 1; i < csvData.get(0).size(); i++) {
      parkingPeriods.add(csvData.get(0).get(i));
    }

    return parkingPeriods;
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
