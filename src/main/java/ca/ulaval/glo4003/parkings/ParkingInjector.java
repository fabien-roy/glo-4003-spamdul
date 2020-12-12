package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.ParkingPeriodPriceAssembler;
import ca.ulaval.glo4003.generators.domain.StringCodeGenerator;
import ca.ulaval.glo4003.parkings.api.ParkingAreaResource;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.infrastructure.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.services.converters.ParkingAreaConverter;
import ca.ulaval.glo4003.parkings.services.converters.ParkingPeriodConverter;
import java.util.List;
import java.util.Map;

public class ParkingInjector {

  private final ParkingStickerCodeGenerator parkingStickerCodeGenerator =
      new ParkingStickerCodeGenerator(new StringCodeGenerator());
  private final ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();
  private final ParkingPeriodConverter parkingPeriodConverter = new ParkingPeriodConverter();

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

  public ParkingStickerCodeAssembler createParkingStickerCodeAssembler() {
    return new ParkingStickerCodeAssembler();
  }

  public ParkingAreaCodeAssembler createParkingAreaCodeAssembler() {
    return new ParkingAreaCodeAssembler();
  }

  public ParkingStickerService createParkingStickerService(
      boolean isDev,
      AccountService accountService,
      List<ParkingStickerCreationObserver> parkingStickerCreationObservers,
      BillService billService) {
    ParkingAreaCodeAssembler parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();

    if (isDev) {
      addParkingAreasToRepository();
    }

    ParkingStickerFactory parkingStickerFactory =
        new ParkingStickerFactory(parkingStickerCodeGenerator);

    ParkingStickerService parkingStickerService =
        new ParkingStickerService(
            parkingStickerFactory, accountService, parkingAreaRepository, billService);
    parkingStickerCreationObservers.forEach(parkingStickerService::register);

    return parkingStickerService;
  }

  private void addParkingAreasToRepository() {
    ParkingAreaConverter parkingAreaConverter =
        new ParkingAreaConverter(createParkingAreaCodeAssembler(), parkingPeriodConverter);

    StringMatrixFileReader fileReader = new CsvFileReader();
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);
    Map<String, Map<String, Double>> zonesAndFees =
        zoneFeesFileHelper.getZoneAndFeesForParkingSticker();

    List<ParkingArea> parkingAreas = parkingAreaConverter.convert(zonesAndFees);
    parkingAreas.forEach(parkingAreaRepository::save);
  }
}
