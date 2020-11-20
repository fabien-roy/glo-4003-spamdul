package ca.ulaval.glo4003.accesspasses;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.times.services.SemesterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassInjectorTest {
  @Mock private CarService carService;
  @Mock private ParkingAreaService parkingAreaService;
  @Mock private AccountService accountService;
  @Mock private BillService billService;
  @Mock private SemesterService semesterService;

  private AccessPassInjector accessPassInjector;

  @Before
  public void setUp() {
    accessPassInjector = new AccessPassInjector();
  }

  @Test
  public void whenGettingAccessPassService_thenReturnIt() {
    AccessPassService accessResource =
        accessPassInjector.createAccessPassService(
            carService, parkingAreaService, accountService, billService, semesterService);

    assertThat(accessResource).isNotNull();
  }
}
