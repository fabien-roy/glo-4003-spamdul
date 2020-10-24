package ca.ulaval.glo4003.accesspasses;

import ca.ulaval.glo4003.accesspasses.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.accesspasses.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCodeGenerator;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassFactory;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.infrastructure.AccessPassInMemoryRepository;
import ca.ulaval.glo4003.accesspasses.infrastructure.AccessPassTypeInMemoryRepository;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.assemblers.ConsumptionAssembler;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.ConsommationType;
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

public class AccessPassInjector {

  private final AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository =
      new AccessPassTypeInMemoryRepository();
  private final AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private final AccessPassCodeGenerator accessPassCodeGenerator = new AccessPassCodeGenerator();
  private final StringMatrixFileReader fileReader = new CsvFileReader();
  private final ConsumptionAssembler consumptionAssembler = new ConsumptionAssembler();

  public AccessPassInjector() {
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
    ZoneFeesFileHelper zoneFeesFileHelper = new ZoneFeesFileHelper(fileReader);

    Map<String, Map<String, Double>> zonesAndFees =
        zoneFeesFileHelper.getZoneAndFeesForAccessPass();
    List<AccessPassType> accessConsumption = new ArrayList<>();

    zonesAndFees
        .keySet()
        .forEach(
            consommation -> {
              ConsommationType consommationType = ConsommationType.get(consommation);
              Map<AccessPeriod, Money> feesPerPeriod = new HashMap<>();
              zonesAndFees
                  .get(consommation)
                  .keySet()
                  .forEach(
                      period -> {
                        AccessPeriod accessPeriod =
                            AccessPeriod.get(period); // TODO translate in english
                        Money fee = Money.fromDouble(zonesAndFees.get(consommation).get(period));
                        feesPerPeriod.put(accessPeriod, fee);
                      });
              accessConsumption.add(
                  new AccessPassType(
                      consumptionAssembler.assemble(consommationType), feesPerPeriod));
            });

    accessConsumption.forEach(accessPassPriceByCarConsumptionInMemoryRepository::save);
  }
}
