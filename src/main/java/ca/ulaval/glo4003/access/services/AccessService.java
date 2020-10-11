package ca.ulaval.glo4003.access.services;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.*;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;

public class AccessService {
  private AccessPassAssembler accessPassAssembler;
  private AccessPassFactory accessPassFactory;
  private CarService carService;
  private AccessPassTypeRepository accessPassTypeRepository;
  private BillService billService;
  private AccountService accountService;
  private AccessPassRepository accessPassRepository;
  private AccessPassCodeAssembler accessPassCodeAssembler;
  private AccountIdAssembler accountIdAssembler;

  public AccessService(
      AccessPassAssembler accessPassAssembler,
      AccessPassFactory accessPassFactory,
      CarService carService,
      AccessPassTypeRepository accessPassTypeRepository,
      AccountService accountService,
      BillService billService,
      AccessPassRepository accessPassRepository,
      AccessPassCodeAssembler accessPassCodeAssembler,
      AccountIdAssembler accountIdAssembler) {

    this.accessPassAssembler = accessPassAssembler;
    this.accessPassFactory = accessPassFactory;
    this.carService = carService;
    this.accessPassTypeRepository = accessPassTypeRepository;
    this.accountService = accountService;
    this.billService = billService;
    this.accessPassRepository = accessPassRepository;
    this.accessPassCodeAssembler = accessPassCodeAssembler;
    this.accountIdAssembler = accountIdAssembler;
  }

  public AccessPassCodeDto addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    accountService.getAccount(id);

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId);
    accessPass = accessPassFactory.create(accessPass);

    LicensePlate licensePlate = accessPass.getLicensePlate();

    ConsumptionTypes consumptionTypes;
    if (licensePlate == null) {
      consumptionTypes = ConsumptionTypes.ZERO_POLLUTION;
    } else {
      Car car = carService.getCarByLicensePlate(licensePlate);
      consumptionTypes = car.getConsumptionType();
    }

    AccessPassType accessPassType =
        accessPassTypeRepository.findByConsumptionType(consumptionTypes);

    Money moneyDue = accessPassType.getFeeForPeriod(AccessPeriods.ONE_DAY);
    BillId billId = billService.addBillForAccessCode(moneyDue, accessPass.getAccessPassCode());
    accountService.addAccessCodeToAccount(id, accessPass.getAccessPassCode(), billId);

    AccessPassCode accessPassCode = accessPassRepository.save(accessPass);

    return accessPassCodeAssembler.assemble(accessPassCode);
  }
}
