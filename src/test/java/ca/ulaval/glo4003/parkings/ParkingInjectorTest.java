package ca.ulaval.glo4003.parkings;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import com.google.common.truth.Truth;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingInjectorTest {

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private PostalCodeAssembler postalCodeAssembler;
  @Mock private EmailAddressAssembler emailAddressAssembler;
  @Mock private AccountService accountService;
  @Mock private ParkingStickerCreationObserver parkingStickerCreationObserver;
  @Mock private BillService billService;

  private ParkingInjector parkingInjector;

  @Before
  public void setUp() {
    parkingInjector = new ParkingInjector();
  }

  @Test
  public void whenCreatingParkingStickerService_thenReturnIt() {
    ParkingStickerService parkingStickerService =
        parkingInjector.createParkingStickerService(
            false,
            accountIdAssembler,
            postalCodeAssembler,
            emailAddressAssembler,
            accountService,
            Collections.singletonList(parkingStickerCreationObserver),
            billService);

    Truth.assertThat(parkingStickerService).isNotNull();
  }
}
