package ca.ulaval.glo4003.injection.parking;

import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaFakeFactory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingStickerRepositoryInMemory;
import java.util.List;

public class ParkingResourceConfig {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;

  public ParkingResourceConfig() {
    parkingStickerCodeGenerator = new ParkingStickerCodeGenerator();
    parkingAreaRepository = new ParkingAreaRepositoryInMemory();
    parkingStickerRepository = new ParkingStickerRepositoryInMemory();
  }

  public ParkingResource createParkingResource(
      boolean isDev,
      AccountIdAssembler accountIdAssembler,
      PostalCodeAssembler postalCodeAssembler,
      AccountRepository accountRepository) {
    if (isDev) {
      ParkingAreaFakeFactory parkingAreaFakeFactory = new ParkingAreaFakeFactory();
      List<ParkingArea> parkingAreas = parkingAreaFakeFactory.createMockData();
      parkingAreas.forEach(parkingAreaRepository::save);
    }

    ParkingAreaCodeAssembler parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
    ParkingStickerAssembler parkingStickerAssembler =
        new ParkingStickerAssembler(
            parkingAreaCodeAssembler, accountIdAssembler, postalCodeAssembler);
    ParkingStickerCodeAssembler parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    AccessStatusAssembler accessStatusAssembler = new AccessStatusAssembler();

    ParkingStickerFactory parkingStickerFactory =
        new ParkingStickerFactory(parkingStickerCodeGenerator);

    ParkingService parkingService =
        new ParkingService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountRepository,
            parkingAreaRepository,
            parkingStickerRepository,
            accessStatusAssembler);

    return new ParkingResourceImplementation(parkingService);
  }
}
