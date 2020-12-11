package ca.ulaval.glo4003.parkings.services.converters;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriodInFrench;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingAreaConverter {
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final ParkingPeriodConverter parkingPeriodConverter;

  public ParkingAreaConverter(
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      ParkingPeriodConverter parkingPeriodConverter) {
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.parkingPeriodConverter = parkingPeriodConverter;
  }

  // TODO test

  public List<ParkingArea> convert(Map<String, Map<String, Double>> zonesAndFees) {
    List<ParkingArea> parkingAreas = new ArrayList<>();
    for (Map.Entry<String, Map<String, Double>> zoneAndFee : zonesAndFees.entrySet()) {
      String zone = zoneAndFee.getKey();
      ParkingAreaCode parkingAreaCode = parkingAreaCodeAssembler.assemble(zone);

      Map<ParkingPeriod, Money> feeForPeriod = getParkingFeeByPeriodForZone(zoneAndFee.getValue());
      parkingAreas.add(new ParkingArea(parkingAreaCode, feeForPeriod));
    }

    return parkingAreas;
  }

  private Map<ParkingPeriod, Money> getParkingFeeByPeriodForZone(Map<String, Double> zoneAndFee) {
    Map<ParkingPeriod, Money> feesPerPeriod = new HashMap<>();
    for (Map.Entry<String, Double> feeForPeriod : zoneAndFee.entrySet()) {
      ParkingPeriodInFrench parkingPeriod = ParkingPeriodInFrench.get(feeForPeriod.getKey());
      Money fee = Money.fromDouble(feeForPeriod.getValue());

      feesPerPeriod.put(parkingPeriodConverter.convert(parkingPeriod), fee);
    }

    return feesPerPeriod;
  }
}
