package ca.ulaval.glo4003.injection.parking;

import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.domain.Email.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.Email.EmailSender;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceConfigTest {

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private PostalCodeAssembler postalCodeAssembler;
  @Mock private AccountRepository accountRepository;
  @Mock private EmailAddressAssembler emailAddressAssembler;
  @Mock private EmailSender emailSender;

  private ParkingResourceConfig parkingResourceConfig;

  @Before
  public void setUp() {
    parkingResourceConfig = new ParkingResourceConfig();
  }

  @Test
  public void whenCreatingParkingResource_thenReturnIt() {
    ParkingResource parkingResource =
        parkingResourceConfig.createParkingResource(
            false,
            accountIdAssembler,
            postalCodeAssembler,
            emailAddressAssembler,
            accountRepository,
            emailSender);

    Truth.assertThat(parkingResource).isNotNull();
  }
}
