package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.bills.filesystem.CSVBillingZoneHelper;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.parkings.api.ParkingResource;
import ca.ulaval.glo4003.parkings.api.ParkingResourceImplementation;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingStickerRepositoryInMemory;
import ca.ulaval.glo4003.parkings.services.ParkingService;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingInjector {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public ParkingInjector() {
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
