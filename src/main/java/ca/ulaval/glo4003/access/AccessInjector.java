package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
import ca.ulaval.glo4003.access.api.AccessResourceImplementation;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.AccessPassCodeGenerator;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import ca.ulaval.glo4003.access.domain.AccessPassPriceByCarConsumption;
import ca.ulaval.glo4003.access.domain.AccessPeriods;
import ca.ulaval.glo4003.access.infrastructure.AccessPassInMemoryRepository;
import ca.ulaval.glo4003.access.infrastructure.AccessPassPriceByCarConsumptionInMemoryRepository;
import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
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

  private final AccessPassPriceByCarConsumptionInMemoryRepository
      accessPassPriceByCarConsumptionInMemoryRepository =
          new AccessPassPriceByCarConsumptionInMemoryRepository();
  private final AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private final AccessPassCodeGenerator accessPassCodeGenerator = new AccessPassCodeGenerator();
  private final String accessPriceFilePath = "data/frais-acces.csv";

  public AccessInjector() {
    addAccessPassByConsumptionTypesToRepository();
  }

  public AccessResource createAccessResource(
      CarService carService, AccountService accountService, BillService billService) {
    AccessPassAssembler accessPassAssembler = new AccessPassAssembler();
    AccessPassFactory accessPassFactory = new AccessPassFactory(accessPassCodeGenerator);
    AccessPassCodeAssembler accessPassCodeAssembler = new AccessPassCodeAssembler();

    AccessService accessService =
        new AccessService(
            accessPassAssembler,
            accessPassFactory,
            carService,
            accessPassPriceByCarConsumptionInMemoryRepository,
            accountService,
            billService,
            accessPassInMemoryRepository,
            accessPassCodeAssembler);
    return new AccessResourceImplementation(accessService);
  }

  private void addAccessPassByConsumptionTypesToRepository() {
    StringMatrixFileReader fileReader = new CsvFileReader();
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader, accessPriceFilePath);

    Map<String, Map<String, Double>> zonesAndFees = zoneFeesFileHelper.getZonesAndFees();
    List<AccessPassPriceByCarConsumption> accessConsumption = new ArrayList<>();

    zonesAndFees
        .keySet()
        .forEach(
            consumptionType -> {
              ConsumptionTypes consumptionTypes = ConsumptionTypes.get(consumptionType);
              Map<AccessPeriods, Money> feesPerPeriod = new HashMap<>();
              zonesAndFees
                  .get(consumptionType)
                  .keySet()
                  .forEach(
                      period -> {
                        AccessPeriods accessPeriods = AccessPeriods.get(period);
                        Money fee =
                            new Money(
                                zonesAndFees
                                    .get(consumptionType)
                                    .get(period)); // TODO : We could use money assembler (but this
                        // is the injector, it's not that important)
                        feesPerPeriod.put(accessPeriods, fee);
                      });
              accessConsumption.add(
                  new AccessPassPriceByCarConsumption(consumptionTypes, feesPerPeriod));
            });

    accessConsumption.forEach(accessPassPriceByCarConsumptionInMemoryRepository::save);
  }
}
