package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaCodeDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAreaCodeService {
  private ParkingAreaRepository parkingAreaRepository;
  private ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public ParkingAreaCodeService(
      ParkingAreaRepository parkingAreaRepository,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
  }

  public List<ParkingAreaCodeDto> getParkingAreas() {
    List<ParkingAreaCode> parkingAreas = parkingAreaRepository.getAllAreaCode();

    return parkingAreas.stream()
        .map(parkingAreaCode -> this.parkingAreaCodeAssembler.assemble(parkingAreaCode))
        .collect(Collectors.toList());
  }
}
