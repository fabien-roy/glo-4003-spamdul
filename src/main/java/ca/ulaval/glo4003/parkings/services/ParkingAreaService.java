package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAreaService {
  private ParkingAreaRepository parkingAreaRepository;
  private ParkingAreaAssembler parkingAreaAssembler;

  public ParkingAreaService(
      ParkingAreaRepository parkingAreaRepository, ParkingAreaAssembler parkingAreaAssembler) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingAreaAssembler = parkingAreaAssembler;
  }

  public List<ParkingAreaDto> getParkingAreas() {
    List<ParkingArea> parkingAreas = parkingAreaRepository.getAllArea();

    return parkingAreas.stream()
        .map(parkingAreaCode -> this.parkingAreaAssembler.assemble(parkingAreaCode))
        .collect(Collectors.toList());
  }
}
