package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.files.filesystem.JsonHelper;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.api.OffenseTypeResource;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseInjectorTest {

  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private TimeOfDayAssembler timeOfDayAssembler;

  private OffenseInjector offenseInjector;

  private final JsonHelper jsonHelper =
      new JsonHelper(); // TODO : If we would not fill offense type repository at injection, this
  // would not have to happen

  @Before
  public void setUp() {
    offenseInjector = new OffenseInjector();
  }

  @Test
  public void whenCreatingOffenseResource_thenReturnIt() {
    OffenseResource offenseResource =
        offenseInjector.createOffenseResource(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler);

    Truth.assertThat(offenseResource).isNotNull();
  }

  @Test
  public void whenCreatingOffenseTypeResource_thenReturnIt() {
    OffenseTypeResource offenseTypeResource =
        offenseInjector.createOffenseTypeResource(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler,
            jsonHelper);

    Truth.assertThat(offenseTypeResource).isNotNull();
  }
}
