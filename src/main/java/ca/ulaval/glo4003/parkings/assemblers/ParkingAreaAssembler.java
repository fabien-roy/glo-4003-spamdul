package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.funds.assemblers.ParkingPeriodPriceAssembler;
import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAreaAssembler {
  private final ParkingPeriodPriceAssembler parkingPeriodPriceAssembler;

  public ParkingAreaAssembler(ParkingPeriodPriceAssembler parkingPeriodPriceAssembler) {
    this.parkingPeriodPriceAssembler = parkingPeriodPriceAssembler;
  }

  private ParkingAreaDto assemble(ParkingArea parkingArea) {
    ParkingAreaDto parkingAreaDto = new ParkingAreaDto();
    parkingAreaDto.parkingArea = parkingArea.getCode().toString();

    parkingAreaDto.parkingPeriodPrice =
        parkingPeriodPriceAssembler.assemble(parkingArea.getFeeForAllPeriod());

    return parkingAreaDto;
  }

  public List<ParkingAreaDto> assembleMany(List<ParkingArea> parkingAreas) {
    return parkingAreas.stream().map(this::assemble).collect(Collectors.toList());
  }
}
