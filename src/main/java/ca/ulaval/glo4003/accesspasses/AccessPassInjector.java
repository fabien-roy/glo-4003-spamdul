package ca.ulaval.glo4003.accesspasses;

import ca.ulaval.glo4003.accesspasses.domain.*;
import ca.ulaval.glo4003.accesspasses.infrastructure.AccessPassTypeInMemoryRepository;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accesspasses.services.assemblers.AccessPassCodeAssembler;
import ca.ulaval.glo4003.accesspasses.services.converters.AccessPassConverter;
import ca.ulaval.glo4003.accesspasses.services.converters.AccessPassPeriodConverter;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.converters.ConsumptionConverter;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.generators.domain.StringCodeGenerator;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessPassInjector {

  private final AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository =
      new AccessPassTypeInMemoryRepository();
  private final AccessPassCodeGenerator accessPassCodeGenerator =
      new AccessPassCodeGenerator(new StringCodeGenerator());
  private final StringMatrixFileReader fileReader = new CsvFileReader();
  private final ConsumptionConverter consumptionConverter = new ConsumptionConverter();
  private final AccessPassPeriodConverter accessPassPeriodConverter =
      new AccessPassPeriodConverter();

  public AccessPassInjector() {
    addAccessPassByConsumptionTypesToRepository();
  }

  public AccessPassService createAccessPassService(
      CarService carService,
      ParkingAreaService parkingAreaService,
      AccountService accountService,
      BillService billService,
      SemesterService semesterService) {
    LicensePlateConverter licensePlateConverter = new LicensePlateConverter();
    SemesterCodeConverter semesterCodeConverter = new SemesterCodeConverter();
    AccessPassConverter accessPassConverter =
        new AccessPassConverter(
            licensePlateConverter,
            semesterService,
            semesterCodeConverter,
            new ParkingAreaCodeAssembler());
    AccessPassFactory accessPassFactory = new AccessPassFactory(accessPassCodeGenerator);
    AccessPassCodeAssembler accessPassCodeAssembler = new AccessPassCodeAssembler();

    return new AccessPassService(
        accessPassConverter,
        accessPassFactory,
        carService,
        parkingAreaService,
        accessPassPriceByCarConsumptionInMemoryRepository,
        accountService,
        billService,
        accessPassCodeAssembler);
  }

  private void addAccessPassByConsumptionTypesToRepository() {
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);

    Map<String, Map<String, Double>> zonesAndFees =
        zoneFeesFileHelper.getZoneAndFeesForAccessPass();
    List<AccessPassType> accessConsumption = new ArrayList<>();

    zonesAndFees
        .keySet()
        .forEach(
            consumption -> {
              ConsumptionTypeInFrench consumptionTypeInFrench =
                  ConsumptionTypeInFrench.get(consumption);
              Map<AccessPeriod, Money> feesPerPeriod = new HashMap<>();
              zonesAndFees
                  .get(consumption)
                  .keySet()
                  .forEach(
                      period -> {
                        AccessPeriodInFrench accessPeriod = AccessPeriodInFrench.get(period);
                        Money fee = Money.fromDouble(zonesAndFees.get(consumption).get(period));
                        feesPerPeriod.put(accessPassPeriodConverter.convert(accessPeriod), fee);
                      });
              accessConsumption.add(
                  new AccessPassType(
                      consumptionConverter.convert(consumptionTypeInFrench), feesPerPeriod));
            });

    accessConsumption.forEach(accessPassPriceByCarConsumptionInMemoryRepository::save);
  }
}
