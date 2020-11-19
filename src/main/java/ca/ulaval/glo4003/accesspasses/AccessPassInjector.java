package ca.ulaval.glo4003.accesspasses;

import ca.ulaval.glo4003.accesspasses.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.accesspasses.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.accesspasses.assembler.AccessPassPeriodAssembler;
import ca.ulaval.glo4003.accesspasses.domain.*;
import ca.ulaval.glo4003.accesspasses.infrastructure.AccessPassInMemoryRepository;
import ca.ulaval.glo4003.accesspasses.infrastructure.AccessPassTypeInMemoryRepository;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.assemblers.ConsumptionAssembler;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;
import ca.ulaval.glo4003.times.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.services.SemesterService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessPassInjector {

  private final AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository =
      new AccessPassTypeInMemoryRepository();
  private final AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private final AccessPassCodeGenerator accessPassCodeGenerator =
      new AccessPassCodeGenerator(new StringCodeGenerator());
  private final StringMatrixFileReader fileReader = new CsvFileReader();
  private final ConsumptionAssembler consumptionAssembler = new ConsumptionAssembler();
  private final AccessPassPeriodAssembler accessPassPeriodAssembler =
      new AccessPassPeriodAssembler();

  public AccessPassInjector() {
    addAccessPassByConsumptionTypesToRepository();
  }

  public AccessPassService createAccessPassService(
      CarService carService,
      AccountService accountService,
      BillService billService,
      SemesterService semesterService) {
    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    SemesterCodeAssembler semesterCodeAssembler = new SemesterCodeAssembler();
    AccessPassAssembler accessPassAssembler =
        new AccessPassAssembler(
            accountIdAssembler, licensePlateAssembler, semesterService, semesterCodeAssembler);
    AccessPassFactory accessPassFactory = new AccessPassFactory(accessPassCodeGenerator);
    AccessPassCodeAssembler accessPassCodeAssembler = new AccessPassCodeAssembler();

    return new AccessPassService(
        accessPassAssembler,
        accessPassFactory,
        carService,
        accessPassPriceByCarConsumptionInMemoryRepository,
        accountService,
        billService,
        accessPassInMemoryRepository,
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
                        feesPerPeriod.put(accessPassPeriodAssembler.assemble(accessPeriod), fee);
                      });
              accessConsumption.add(
                  new AccessPassType(
                      consumptionAssembler.assemble(consumptionTypeInFrench), feesPerPeriod));
            });

    accessConsumption.forEach(accessPassPriceByCarConsumptionInMemoryRepository::save);
  }
}
