package ca.ulaval.glo4003.offenses;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseInjectorTest {

  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private MoneyConverter moneyConverter;
  @Mock private BillService billService;
  @Mock private AccountService accountService;

  private OffenseInjector offenseInjector;

  @Before
  public void setUp() {
    offenseInjector = new OffenseInjector();
  }

  @Test
  public void whenCreatingOffenseResource_thenReturnIt() {
    OffenseResource offenseResource =
        offenseInjector.createOffenseResource(
            parkingAreaRepository, moneyConverter, billService, accountService);

    assertThat(offenseResource).isNotNull();
  }
}
