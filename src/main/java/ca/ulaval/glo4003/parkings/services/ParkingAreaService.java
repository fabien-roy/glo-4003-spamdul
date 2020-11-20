package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAreaService {
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingAreaAssembler parkingAreaAssembler;

  public ParkingAreaService(
      ParkingAreaRepository parkingAreaRepository, ParkingAreaAssembler parkingAreaAssembler) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingAreaAssembler = parkingAreaAssembler;
  }

  public ParkingArea get(ParkingAreaCode parkingAreaCode) {
    return parkingAreaRepository.get(parkingAreaCode);
  }

  public List<ParkingAreaDto> getParkingAreas() {
    List<ParkingArea> parkingAreas = parkingAreaRepository.getAll();

    return parkingAreaAssembler.assembleMany(parkingAreas);
  }

  public List<ParkingAreaCode> getParkingAreaCodes() {
    List<ParkingArea> parkingAreas = parkingAreaRepository.getAll();

    return parkingAreas.stream().map(ParkingArea::getCode).collect(Collectors.toList());
  }
}
