package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseInjectorTest {

  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private TimeOfDayAssembler timeOfDayAssembler;
  @Mock private MoneyAssembler moneyAssembler;
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
            parkingAreaRepository,
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler,
            moneyAssembler,
            billService,
            accountService);

    Truth.assertThat(offenseResource).isNotNull();
  }
}
