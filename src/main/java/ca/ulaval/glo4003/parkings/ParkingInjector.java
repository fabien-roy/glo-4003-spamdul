package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.ParkingPeriodPriceAssembler;
import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.api.ParkingAreaResource;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingStickerRepositoryInMemory;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingPeriodAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.services.converters.ParkingPeriodConverter;
import ca.ulaval.glo4003.parkings.services.converters.ParkingStickerConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingInjector {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator =
      new ParkingStickerCodeGenerator(new StringCodeGenerator());
  private final ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();
  private final ParkingStickerRepository parkingStickerRepository =
      new ParkingStickerRepositoryInMemory();

  public ParkingAreaRepository getParkingAreaRepository() {
    return parkingAreaRepository;
  }

  public ParkingAreaResource createParkingAreaResource() {
    return new ParkingAreaResource(createParkingAreaService());
  }

  public ParkingAreaService createParkingAreaService() {
    return new ParkingAreaService(
        parkingAreaRepository, new ParkingAreaAssembler(new ParkingPeriodPriceAssembler()));
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
      AccountIdConverter accountIdConverter,
      PostalCodeConverter postalCodeConverter,
      EmailAddressConverter emailAddressConverter,
      AccountService accountService,
      List<ParkingStickerCreationObserver> parkingStickerCreationObservers,
      BillService billService) {
    ParkingAreaCodeAssembler parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();

    if (isDev) {
      addParkingAreasToRepository(parkingAreaCodeAssembler);
    }

    ParkingStickerFactory parkingStickerFactory =
        new ParkingStickerFactory(parkingStickerCodeGenerator);
    ParkingStickerConverter parkingStickerConverter =
        new ParkingStickerConverter(
            parkingAreaCodeAssembler,
            accountIdConverter,
            postalCodeConverter,
            emailAddressConverter,
            new ParkingPeriodAssembler());
    ParkingStickerCodeAssembler parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();

    ParkingStickerService parkingStickerService =
        new ParkingStickerService(
            parkingStickerConverter,
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

    ParkingPeriodConverter parkingPeriodConverter = new ParkingPeriodConverter();

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
                        ParkingPeriodInFrench parkingPeriod = ParkingPeriodInFrench.get(period);
                        Money fee = Money.fromDouble(zonesAndFees.get(zone).get(period));
                        feesPerPeriod.put(parkingPeriodConverter.convert(parkingPeriod), fee);
                      });
              parkingAreas.add(new ParkingArea(parkingAreaCode, feesPerPeriod));
            });

    parkingAreas.forEach(parkingAreaRepository::save);
  }
}
