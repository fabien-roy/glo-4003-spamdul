package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.profits.api.ProfitsResourceImplementation;
import ca.ulaval.glo4003.profits.services.ProfitsService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsResourceImplementationTest {
  @Mock private ProfitsService profitsService;

  private ProfitsResourceImplementation profitsResourceImplementation;

  @Before
  public void setUp() {
    profitsResourceImplementation = new ProfitsResourceImplementation(profitsService);
  }
}
