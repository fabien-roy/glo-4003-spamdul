package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.AccessPassCodeGenerator;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.access.domain.AccessPeriod;
import ca.ulaval.glo4003.access.infrastructure.AccessPassInMemoryRepository;
import ca.ulaval.glo4003.access.infrastructure.AccessPassTypeInMemoryRepository;
import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.files.domain.StringMatrixFileReader;
import ca.ulaval.glo4003.files.filesystem.CsvFileReader;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.filesystem.ZoneFeesFileHelper;
import ca.ulaval.glo4003.funds.services.BillService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessInjector {

  private final AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository =
      new AccessPassTypeInMemoryRepository();
  private final AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private final AccessPassCodeGenerator accessPassCodeGenerator = new AccessPassCodeGenerator();

  public AccessInjector() {
    addAccessPassByConsumptionTypesToRepository();
  }

  public AccessPassService createAccessPassService(
      CarService carService, AccountService accountService, BillService billService) {
    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    AccessPassAssembler accessPassAssembler =
        new AccessPassAssembler(accountIdAssembler, licensePlateAssembler);
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
    StringMatrixFileReader fileReader = new CsvFileReader();
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);

    Map<String, Map<String, Double>> zonesAndFees =
        zoneFeesFileHelper.getZoneAndFeesForAccessPass();
    List<AccessPassType> accessConsumption = new ArrayList<>();

    zonesAndFees
        .keySet()
        .forEach(
            consumptionType -> {
              ConsumptionType consumptionTypes = ConsumptionType.get(consumptionType);
              Map<AccessPeriod, Money> feesPerPeriod = new HashMap<>();
              zonesAndFees
                  .get(consumptionType)
                  .keySet()
                  .forEach(
                      period -> {
                        AccessPeriod accessPeriod = AccessPeriod.get(period);
                        Money fee =
                            new Money(
                                zonesAndFees
                                    .get(consumptionType)
                                    .get(period)); // TODO : We could use money assembler (but this
                        // is the injector, it's not that important)
                        feesPerPeriod.put(accessPeriod, fee);
                      });
              accessConsumption.add(new AccessPassType(consumptionTypes, feesPerPeriod));
            });

    accessConsumption.forEach(accessPassPriceByCarConsumptionInMemoryRepository::save);
  }
}
