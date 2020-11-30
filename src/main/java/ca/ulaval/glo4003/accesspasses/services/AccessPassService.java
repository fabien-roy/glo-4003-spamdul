package ca.ulaval.glo4003.accesspasses.services;

import ca.ulaval.glo4003.accesspasses.domain.*;
import ca.ulaval.glo4003.accesspasses.services.converters.AccessPassCodeConverter;
import ca.ulaval.glo4003.accesspasses.services.converters.AccessPassConverter;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import java.util.List;

public class AccessPassService {
  private final AccessPassConverter accessPassConverter;
  private final AccessPassFactory accessPassFactory;
  private final CarService carService;
  private final ParkingAreaService parkingAreaService;
  private final AccessPassTypeRepository accessPassTypeRepository;
  private final BillService billService;
  private final AccountService accountService;
  private final AccessPassCodeConverter accessPassCodeConverter;

  public AccessPassService(
      AccessPassConverter accessPassConverter,
      AccessPassFactory accessPassFactory,
      CarService carService,
      ParkingAreaService parkingAreaService,
      AccessPassTypeRepository accessPassTypeRepository,
      AccountService accountService,
      BillService billService,
      AccessPassCodeConverter accessPassCodeConverter) {
    this.accessPassConverter = accessPassConverter;
    this.accessPassFactory = accessPassFactory;
    this.carService = carService;
    this.parkingAreaService = parkingAreaService;
    this.accessPassTypeRepository = accessPassTypeRepository;
    this.accountService = accountService;
    this.billService = billService;
    this.accessPassCodeConverter = accessPassCodeConverter;
  }

  public AccessPassCodeDto addAccessPass(AccessPassDto accessPassDto, String accountId) {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto);
    Account account = accountService.getAccount(accountId);
    LicensePlate licensePlate = accessPass.getLicensePlate();

    ConsumptionType consumptionType;
    if (licensePlate == null) {
      consumptionType = ConsumptionType.ZERO_POLLUTION;
    } else {
      Car car = carService.getCar(licensePlate);
      consumptionType = car.getConsumptionType();
      parkingAreaService.get(accessPass.getParkingAreaCode());
    }

    accessPass = accessPassFactory.create(accessPass);

    AccessPassType accessPassType = accessPassTypeRepository.findByConsumptionType(consumptionType);

    Money moneyDue = accessPassType.getFeeForPeriod(AccessPeriod.get(accessPassDto.period));
    BillId billId =
        billService.addBillForAccessCode(moneyDue, accessPass.getCode(), consumptionType);
    accountService.addAccessPassToAccount(account.getId(), accessPass, billId);

    return accessPassCodeConverter.convert(accessPass.getCode());
  }

  public AccessPass getAccessPass(String code) {
    AccessPassCode accessPassCode = accessPassCodeConverter.convert(code);
    return accountService.getAccessPass(accessPassCode);
  }

  public List<AccessPass> getAccessPasses(LicensePlate licensePlate) {
    return accountService.getAccessPasses(licensePlate);
  }

  public void enterCampus(AccessPass accessPass) {
    accessPass.enterCampus();
    accountService.update(accessPass);
  }

  public void exitCampus(AccessPass accessPass) {
    accessPass.exitCampus();
    accountService.update(accessPass);
  }
}
