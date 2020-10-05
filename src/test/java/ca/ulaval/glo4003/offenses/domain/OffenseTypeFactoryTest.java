package ca.ulaval.glo4003.offenses.domain;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.assemblers.OffenseCodeAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeFactoryTest {

  @Mock private OffenseTypeRepository offenseTypeRepository;
  @Mock private OffenseCodeAssembler offenseCodeAssembler;

  private OffenseTypeFactory offenseTypeFactory;

  private final OffenseCode wrongZoneOffenseCode = createOffenseCode();
  private final OffenseCode wrongDayOffenseCode = createOffenseCode();
  private final OffenseCode invalidStickerOffenseCode = createOffenseCode();
  private final OffenseType wrongZoneOffenseType = anOffenseType().build();
  private final OffenseType wrongDayOffenseType = anOffenseType().build();
  private final OffenseType invalidStickerOffenseType = anOffenseType().build();

  @Before
  public void setUp() {
    offenseTypeFactory = new OffenseTypeFactory(offenseTypeRepository, offenseCodeAssembler);

    when(offenseCodeAssembler.assemble("ZONE_01")).thenReturn(wrongZoneOffenseCode);
    when(offenseCodeAssembler.assemble("VIG_01")).thenReturn(wrongDayOffenseCode);
    when(offenseCodeAssembler.assemble("VIG_02")).thenReturn(invalidStickerOffenseCode);
    when(offenseTypeRepository.findByCode(wrongZoneOffenseCode)).thenReturn(wrongZoneOffenseType);
    when(offenseTypeRepository.findByCode(wrongDayOffenseCode)).thenReturn(wrongDayOffenseType);
    when(offenseTypeRepository.findByCode(invalidStickerOffenseCode))
        .thenReturn(invalidStickerOffenseType);
  }

  @Test
  public void whenCreatingWrongZoneOffenseType_thenReturnWrongZoneOffenseType() {
    OffenseType offenseType = offenseTypeFactory.createWrongZoneOffense();

    Truth.assertThat(offenseType).isSameInstanceAs(wrongZoneOffenseType);
  }

  @Test
  public void whenWrongDayOffenseType_thenReturnWrongDayOffenseType() {
    OffenseType offenseType = offenseTypeFactory.createWrongDayOffense();

    Truth.assertThat(offenseType).isSameInstanceAs(wrongDayOffenseType);
  }

  @Test
  public void whenCreatingInvalidStickerOffenseType_thenReturnInvalidStickerOffenseType() {
    OffenseType offenseType = offenseTypeFactory.createInvalidStickerOffense();

    Truth.assertThat(offenseType).isSameInstanceAs(invalidStickerOffenseType);
  }
}
