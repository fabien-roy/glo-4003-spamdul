package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingStickerRepositoryInMemory;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingInjector {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator =
      new ParkingStickerCodeGenerator();
  private final ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();
  private final ParkingStickerRepository parkingStickerRepository =
      new ParkingStickerRepositoryInMemory();

  public ParkingAreaRepository getParkingAreaRepository() {
    return parkingAreaRepository;
  }

  public ParkingStickerRepository getParkingStickerRepository() {
    return parkingStickerRepository;
  }

  public ParkingStickerCodeAssembler createParkingStickerCodeAssembler() {
    return new ParkingStickerCodeAssembler();
  }

  public ParkingAreaCodeAssembler createParkingAreaCodeAssembler() {
    return new ParkingAreaCodeAssembler();
  }

  public ParkingStickerService createParkingStickerService(
      boolean isDev,
      AccountIdAssembler accountIdAssembler,
      PostalCodeAssembler postalCodeAssembler,
      EmailAddressAssembler emailAddressAssembler,
      AccountService accountService,
      List<ParkingStickerCreationObserver> parkingStickerCreationObservers,
      BillService billService) {
    ParkingAreaCodeAssembler parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();

    if (isDev) {
      addParkingAreasToRepository(parkingAreaCodeAssembler);
    }

    ParkingStickerFactory parkingStickerFactory =
        new ParkingStickerFactory(parkingStickerCodeGenerator);
    ParkingStickerAssembler parkingStickerAssembler =
        new ParkingStickerAssembler(
            parkingAreaCodeAssembler,
            accountIdAssembler,
            postalCodeAssembler,
            emailAddressAssembler);
    ParkingStickerCodeAssembler parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();

    ParkingStickerService parkingStickerService =
        new ParkingStickerService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountService,
            parkingAreaRepository,
            parkingStickerRepository,
            billService);
    parkingStickerCreationObservers.forEach(parkingStickerService::register);

    return parkingStickerService;
  }

  private void addParkingAreasToRepository(ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    StringMatrixFileReader fileReader = new CsvFileReader();
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);

    Map<String, Map<String, Double>> zonesAndFees =
        zoneFeesFileHelper.getZoneAndFeesForParkingSticker();
    List<ParkingArea> parkingAreas = new ArrayList<>();

    zonesAndFees
        .keySet()
        .forEach(
            zone -> {
              ParkingAreaCode parkingAreaCode = parkingAreaCodeAssembler.assemble(zone);
              Map<ParkingPeriod, Money> feesPerPeriod = new HashMap<>();
              zonesAndFees
                  .get(zone)
                  .keySet()
                  .forEach(
                      period -> {
                        ParkingPeriod parkingPeriod =
                            ParkingPeriod.get(period); // TODO translate in english
                        Money fee = Money.fromDouble(zonesAndFees.get(zone).get(period));
                        feesPerPeriod.put(parkingPeriod, fee);
                      });
              parkingAreas.add(new ParkingArea(parkingAreaCode, feesPerPeriod));
            });

    parkingAreas.forEach(parkingAreaRepository::save);
  }
}
