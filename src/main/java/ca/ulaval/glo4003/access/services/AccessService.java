package ca.ulaval.glo4003.access.services;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.CarService;

public class AccessService {
  private AccessPassAssembler accessPassAssembler;
  private AccessPassFactory accessPassFactory;
  private CarService carService;

  public AccessService(
      AccessPassAssembler accessPassAssembler,
      AccessPassFactory accessPassFactory,
      CarService carService) {

    this.accessPassAssembler = accessPassAssembler;
    this.accessPassFactory = accessPassFactory;
    this.carService = carService;
  }

  public AccessPassCodeDto addAccessPass(AccessPassDto accessPassDto, String accountId) {

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId);
    accessPass = accessPassFactory.create(accessPass);

    LicensePlate licensePlate = accessPass.getLicensePlate();

    ConsumptionTypes consumptionTypes;
    if (licensePlate == null) {
      consumptionTypes = ConsumptionTypes.ZERO_POLLUTION;
    } else {
      Car car = carService.getCarByLicensePlate(accessPass.getLicensePlate());
      consumptionTypes = car.getConsumptionType();
    }

    return null;
    // get price for car type
    // saveAccessPass
    // saveBill for accessPass
    // updateAccount
    // return accessPassCode
  }
}
