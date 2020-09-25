package ca.ulaval.glo4003.serverConfiguration;

import ca.ulaval.glo4003.api.car.CarResourceImplementation;
import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.api.user.UserResourceImplementation;
import ca.ulaval.glo4003.domain.account.*;
import ca.ulaval.glo4003.domain.car.CarAssembler;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        configureUser();
        configureParking();
        configureCar();

    }

    private void configureUser() {
        //bindAsContract(UserResource.class);
        bind(UserResourceImplementation.class).to(UserResource.class);

        bindAsContract(UserAssembler.class);

        bindAsContract(CustomDateAssembler.class);
        bindAsContract(AccountIdGenerator.class);
        bindAsContract(AccountIdAssembler.class);
        bindAsContract(AccountFactory.class);
        bindAsContract(AccountService.class);

        //bindAsContract(AccountRepository.class);
        bind(AccountRepositoryInMemory.class).to(AccountRepository.class);
    }

  private void configureParking() {
    // bindAsContract(ParkingResource.class);
    bind(ParkingResourceImplementation.class).to(ParkingResource.class);

    // bindAsContract(ParkingAreaRepository.class);
    bind(ParkingAreaRepositoryInMemory.class).to(ParkingAreaRepository.class);

    bindAsContract(ParkingStickerAssembler.class);
    bindAsContract(ParkingStickerCodeAssembler.class);
    bindAsContract(ParkingStickerCodeGenerator.class);
    bindAsContract(ParkingStickerFactory.class);

        }
    private void configureCar() {
        bind(CarResourceImplementation.class).to(ContactResource.class);
        bindAsContract(CarValidator.class);
        bindAsContract(CarAssembler.class);
        bindAsContract(CarService.class);
    }

}
