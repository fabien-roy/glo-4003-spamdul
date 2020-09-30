package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseInjectorTest {

  @Mock private ParkingStickerRepository parkingStickerRepository;
  private OffenseInjector offenseInjector;

  @Before
  public void setUp() {
    offenseInjector = new OffenseInjector();
  }

  @Test
  public void whenCreatingOffenseResource_thenReturnIt() {
    OffenseResource offenseResource =
        offenseInjector.createOffenseResource(parkingStickerRepository);

    Truth.assertThat(offenseResource).isNotNull();
  }
}
