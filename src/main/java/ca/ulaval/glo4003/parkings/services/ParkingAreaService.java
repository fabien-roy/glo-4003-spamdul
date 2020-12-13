package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingConfiguration;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.services.dto.ParkingAreaDto;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAreaService {
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingAreaAssembler parkingAreaAssembler;

  public ParkingAreaService(ParkingAreaRepository parkingAreaRepository) {
    this(parkingAreaRepository, new ParkingAreaAssembler());
  }

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
    List<ParkingAreaCode> parkingAreaCodes =
        parkingAreas.stream().map(ParkingArea::getCode).collect(Collectors.toList());

    ParkingAreaCode bicycleParkingAreaCode =
        ParkingConfiguration.getConfiguration().getBicycleParkingAreaCode();
    parkingAreaCodes.add(bicycleParkingAreaCode);

    return parkingAreaCodes;
  }
}
