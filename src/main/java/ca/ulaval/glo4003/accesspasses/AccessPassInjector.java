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
import java.util.*;

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

    for (Map.Entry<String, Map<String, Double>> zoneAndFee : zonesAndFees.entrySet()) {
      String consumptionType = zoneAndFee.getKey();
      ConsumptionTypeInFrench consumptionTypeInFrench =
          ConsumptionTypeInFrench.get(consumptionType);

      Map<AccessPeriod, Money> feesPerPeriod =
          getFeeByPeriodForConsumptionType(zoneAndFee.getValue());
      saveAccessPassTypeToRepository(consumptionTypeInFrench, feesPerPeriod);
    }
  }

  private Map<AccessPeriod, Money> getFeeByPeriodForConsumptionType(
      Map<String, Double> zoneAndFees) {
    Map<AccessPeriod, Money> feesPerPeriod = new HashMap<>();
    for (Map.Entry<String, Double> zoneAndFee : zoneAndFees.entrySet()) {
      AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.get(zoneAndFee.getKey());
      Money fee = Money.fromDouble(zoneAndFee.getValue());

      feesPerPeriod.put(accessPassPeriodConverter.convert(accessPeriodInFrench), fee);
    }

    return feesPerPeriod;
  }

  private void saveAccessPassTypeToRepository(
      ConsumptionTypeInFrench consumptionTypeInFrench, Map<AccessPeriod, Money> feeForPeriods) {
    AccessPassType accessPassType =
        new AccessPassType(consumptionConverter.convert(consumptionTypeInFrench), feeForPeriods);
    accessPassPriceByCarConsumptionInMemoryRepository.save(accessPassType);
  }
}
