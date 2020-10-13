package ca.ulaval.glo4003.access.services;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.access.domain.*;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;

public class AccessPassService {
  private final AccessPassAssembler accessPassAssembler;
  private final AccessPassFactory accessPassFactory;
  private final CarService carService;
  private final AccessPassTypeRepository accessPassTypeRepository;
  private final BillService billService;
  private final AccountService accountService;
  private final AccessPassRepository accessPassRepository;
  private final AccessPassCodeAssembler accessPassCodeAssembler;

  public AccessPassService(
      AccessPassAssembler accessPassAssembler,
      AccessPassFactory accessPassFactory,
      CarService carService,
      AccessPassTypeRepository accessPassTypeRepository,
      AccountService accountService,
      BillService billService,
      AccessPassRepository accessPassRepository,
      AccessPassCodeAssembler accessPassCodeAssembler) {
    this.accessPassAssembler = accessPassAssembler;
    this.accessPassFactory = accessPassFactory;
    this.carService = carService;
    this.accessPassTypeRepository = accessPassTypeRepository;
    this.accountService = accountService;
    this.billService = billService;
    this.accessPassRepository = accessPassRepository;
    this.accessPassCodeAssembler = accessPassCodeAssembler;
  }

  public AccessPassCodeDto addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId);
    Account account = accountService.getAccount(accountId);
    LicensePlate licensePlate = accessPass.getLicensePlate();

    ConsumptionTypes consumptionType;
    if (licensePlate == null) {
      consumptionType = ConsumptionTypes.ZERO_POLLUTION;
    } else {
      Car car = carService.getCar(licensePlate);
      consumptionType = car.getConsumptionType();
    }

    accessPass = accessPassFactory.create(accessPass);

    AccessPassType accessPassType = accessPassTypeRepository.findByConsumptionType(consumptionType);

    Money moneyDue = accessPassType.getFeeForPeriod(AccessPeriods.ONE_DAY);
    BillId billId = billService.addBillForAccessCode(moneyDue, accessPass.getCode());
    accountService.addAccessCodeToAccount(account.getId(), accessPass.getCode(), billId);

    AccessPassCode accessPassCode = accessPassRepository.save(accessPass);

    return accessPassCodeAssembler.assemble(accessPassCode);
  }

  public AccessPass getAccessPass(String code) {
    AccessPassCode accessPassCode = accessPassCodeAssembler.assemble(code);
    return accessPassRepository.get(accessPassCode);
  }
}
