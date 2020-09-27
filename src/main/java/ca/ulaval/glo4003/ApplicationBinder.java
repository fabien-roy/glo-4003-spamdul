package ca.ulaval.glo4003;

import ca.ulaval.glo4003.api.car.CarResource;
import ca.ulaval.glo4003.api.car.CarResourceImplementation;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.api.user.UserResourceImplementation;
import ca.ulaval.glo4003.domain.account.*;
import ca.ulaval.glo4003.domain.car.CarAssembler;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.UserService;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaFakeFactory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingStickerRepositoryInMemory;
import java.util.List;
import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
  private static final boolean IS_DEV = true;

  @Override
  protected void configure() {
    configureUser();
    configureParking();
    configureCar();
    configureAccount();
    configureLocation();
    configureTime();
  }

  private void configureUser() {
    bind(UserResourceImplementation.class).to(UserResource.class);

    bindAsContract(UserAssembler.class);
    bindAsContract(UserService.class);
  }

  private void configureAccount() {
    bind(AccountRepositoryInMemory.class).to(AccountRepository.class).in(Singleton.class);

    bindAsContract(AccountIdGenerator.class);
    bindAsContract(AccountIdAssembler.class);
    bindAsContract(AccountFactory.class);
    bindAsContract(AccountService.class);
  }

  private void configureParking() {
    bind(ParkingResourceImplementation.class).to(ParkingResource.class);

    bind(createParkingAreaRepository()).to(ParkingAreaRepository.class);
    bind(ParkingStickerRepositoryInMemory.class)
        .to(ParkingStickerRepository.class)
        .in(Singleton.class);

    bindAsContract(ParkingAreaCodeAssembler.class);
    bindAsContract(ParkingStickerAssembler.class);
    bindAsContract(ParkingStickerCodeAssembler.class);
    bindAsContract(ParkingStickerCodeGenerator.class);
    bindAsContract(ParkingStickerFactory.class);
    bindAsContract(ParkingService.class);
    bindAsContract(ParkingStickerFactory.class);
  }

  private ParkingAreaRepository createParkingAreaRepository() {
    ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();

    if (IS_DEV) {
      ParkingAreaFakeFactory parkingAreaFakeFactory = new ParkingAreaFakeFactory();
      List<ParkingArea> parkingAreas = parkingAreaFakeFactory.createMockData();
      parkingAreas.forEach(parkingAreaRepository::save);
    }

    return parkingAreaRepository;
  }

  private void configureCar() {
    bind(CarResourceImplementation.class).to(CarResource.class);

    bindAsContract(CarValidator.class);
    bindAsContract(CarAssembler.class);
    bindAsContract(CarService.class);
  }

  private void configureLocation() {
    bindAsContract(PostalCodeAssembler.class);
  }

  private void configureTime() {
    bindAsContract(CustomDateAssembler.class);
  }
}
