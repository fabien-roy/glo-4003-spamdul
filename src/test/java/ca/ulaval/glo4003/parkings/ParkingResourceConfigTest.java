package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.parkings.api.ParkingResource;
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
  @Mock private EmailAddressAssembler emailAddressAssembler;
  @Mock private EmailSender emailSender;
  @Mock private AccountRepository accountRepository;

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
            emailSender,
            accountRepository);

    Truth.assertThat(parkingResource).isNotNull();
  }
}
