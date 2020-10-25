package ca.ulaval.glo4003.profits;

import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.api.ProfitsResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProfitsInjectorTest {
  @Mock private BillService billService;
  private ProfitsInjector profitsInjector;

  @Before
  public void setUp() {
    profitsInjector = new ProfitsInjector();
  }

  @Test
  public void whenCreatingOffenseResource_thenReturnIt() {
    ProfitsResource profitsResource = profitsInjector.createProfitsResource(billService);

    Truth.assertThat(profitsResource).isNotNull();
  }
}
