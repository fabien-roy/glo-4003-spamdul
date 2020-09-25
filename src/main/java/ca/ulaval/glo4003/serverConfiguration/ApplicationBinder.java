package ca.ulaval.glo4003.serverConfiguration;

import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.api.user.UserResourceImplementation;
import ca.ulaval.glo4003.domain.account.AccountFactory;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountIdGenerator;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaFakeFactory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import java.util.List;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        configureUser();
        configureParking();
    }

    private void configureUser() {
        //bindAsContract(UserResource.class);
        bind(UserResourceImplementation.class).to(UserResource.class);

        bindAsContract(UserAssembler.class);

        bindAsContract(AccountIdGenerator.class);
        bindAsContract(AccountIdAssembler.class);
        bindAsContract(AccountFactory.class);

        //bindAsContract(AccountRepository.class);
        bind(AccountRepositoryInMemory.class).to(AccountRepository.class);

    }

    private void configureParking() {
        //bindAsContract(ParkingResource.class);
        bind(ParkingResourceImplementation.class).to(ParkingResource.class);

        //bindAsContract(ParkingAreaRepository.class);
        bind(ParkingAreaRepositoryInMemory.class).to(ParkingAreaRepository.class);

        bindAsContract(ParkingStickerAssembler.class);
        bindAsContract(ParkingStickerCodeAssembler.class);
        bindAsContract(ParkingStickerCodeGenerator.class);
        bindAsContract(ParkingStickerFactory.class);

        bindAsContract(ParkingService.class);

    }


}
