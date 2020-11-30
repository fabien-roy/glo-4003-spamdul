package ca.ulaval.glo4003.offenses.services.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class OffenseCodeAssemblerTest {

  private OffenseCodeAssembler offenseCodeAssembler;

  private final OffenseCode offenseCode = createOffenseCode();

  @Before
  public void setUp() {
    offenseCodeAssembler = new OffenseCodeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnOffenseCode() {
    OffenseCode assembledOffenseCode = offenseCodeAssembler.assemble(offenseCode.toString());

    Truth.assertThat(assembledOffenseCode).isEqualTo(offenseCode);
  }

  @Test
  public void givenLowerCaseCode_whenAssembling_thenReturnOffenseCode() {
    String lowerCaseOffense = offenseCode.toString().toLowerCase();

    OffenseCode assembledOffenseCode = offenseCodeAssembler.assemble(lowerCaseOffense);

    Truth.assertThat(assembledOffenseCode).isEqualTo(offenseCode);
  }
}
