package ca.ulaval.glo4003.funds.filesystem;

import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZoneFeesFileHelper {

  private List<List<String>> csvData;
  private static final String accessPriceFilePath = "data/frais-acces.csv";
  private static final String parkingStickerPriceFilePath = "data/frais-zone.csv";
  private final StringMatrixFileReader fileReader;

  public ZoneFeesFileHelper(StringMatrixFileReader fileReader) {
    this.fileReader = fileReader;
  }

  public Map<String, Map<String, Double>> getZoneAndFeesForParkingSticker() {
    csvData = fileReader.readFile(parkingStickerPriceFilePath);
    return getZonesAndFees();
  }

  public Map<String, Map<String, Double>> getZoneAndFeesForAccessPass() {
    csvData = fileReader.readFile(accessPriceFilePath);
    return getZonesAndFees();
  }

  private Map<String, Map<String, Double>> getZonesAndFees() {
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

  private List<String> getAllZones() {
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
}
