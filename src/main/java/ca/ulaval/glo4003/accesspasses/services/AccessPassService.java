package ca.ulaval.glo4003.accesspasses.services;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.accesspasses.assembler.AccessPassCodeAssembler;
import ca.ulaval.glo4003.accesspasses.domain.*;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import java.util.List;

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

    ConsumptionType consumptionType;
    if (licensePlate == null) {
      consumptionType = ConsumptionType.ZERO_POLLUTION;
    } else {
      Car car = carService.getCar(licensePlate);
      consumptionType = car.getConsumptionType();
    }

    accessPass = accessPassFactory.create(accessPass);

    AccessPassType accessPassType = accessPassTypeRepository.findByConsumptionType(consumptionType);

    Money moneyDue = accessPassType.getFeeForPeriod(AccessPeriod.ONE_DAY);
    BillId billId =
        billService.addBillForAccessCode(moneyDue, accessPass.getCode(), consumptionType);
    accountService.addAccessCodeToAccount(account.getId(), accessPass.getCode(), billId);

    AccessPassCode accessPassCode = accessPassRepository.save(accessPass);

    return accessPassCodeAssembler.assemble(accessPassCode);
  }

  public AccessPass getAccessPass(String code) {
    AccessPassCode accessPassCode = accessPassCodeAssembler.assemble(code);
    return accessPassRepository.get(accessPassCode);
  }

  public List<AccessPass> getAccessPassesByLicensePlate(LicensePlate licensePlate) {
    return accessPassRepository.get(licensePlate);
  }
}
