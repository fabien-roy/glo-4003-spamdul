package ca.ulaval.glo4003.injection.parking;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.domain.bill.CSVBillingZoneHelper;
import ca.ulaval.glo4003.domain.communication.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.communication.EmailSender;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingStickerRepositoryInMemory;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingResourceConfig {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public ParkingResourceConfig() {
    parkingStickerCodeGenerator = new ParkingStickerCodeGenerator();
    parkingAreaRepository = new ParkingAreaRepositoryInMemory();
    parkingStickerRepository = new ParkingStickerRepositoryInMemory();
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
  }

  public ParkingResource createParkingResource(
      boolean isDev,
      AccountIdAssembler accountIdAssembler,
      PostalCodeAssembler postalCodeAssembler,
      EmailAddressAssembler emailAddressAssembler,
      EmailSender emailSender,
      AccountRepository accountRepository) {
    if (isDev) {
      CSVBillingZoneHelper csvBillingZoneHelper = new CSVBillingZoneHelper();
      List<String> zones = csvBillingZoneHelper.getAllZones();
      List<ParkingArea> parkingAreas =
          zones.stream()
              .map(zone -> new ParkingArea(new ParkingAreaCode(zone)))
              .collect(Collectors.toList());
      parkingAreas.forEach(parkingAreaRepository::save);
    }

    ParkingAreaCodeAssembler parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
    ParkingStickerAssembler parkingStickerAssembler =
        new ParkingStickerAssembler(
            parkingAreaCodeAssembler,
            accountIdAssembler,
            postalCodeAssembler,
            emailAddressAssembler);
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
            accessStatusAssembler,
            emailSender);

    return new ParkingResourceImplementation(parkingService);
  }

  public ParkingStickerRepository getParkingStickerRepository() {
    return parkingStickerRepository;
  }

  public ParkingStickerCodeAssembler getParkingStickerCodeAssembler() {
    return parkingStickerCodeAssembler;
  }

  public ParkingAreaCodeAssembler getParkingAreaCodeAssembler() {
    return parkingAreaCodeAssembler;
  }
}
