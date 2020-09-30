package ca.ulaval.glo4003.offenses.assemblers;

import static ca.ulaval.glo4003.offenses.helpers.OffenseBuilder.anOffense;
import static ca.ulaval.glo4003.offenses.helpers.OffenseMother.*;

import ca.ulaval.glo4003.offenses.api.dto.OffenseDto;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseAssemblerTest {
  private static final String REASON_TEXT = createReasonText();
  private static final OffenseCodes REASON_CODE = createReasonCode();
  private static final double AMOUNT = createAmount();

  private Offense offense;

  private OffenseAssembler offenseAssembler;

  @Before
  public void setUp() {
    offenseAssembler = new OffenseAssembler();

    offense =
        anOffense()
            .withReasonText(REASON_TEXT)
            .withReasonCode(REASON_CODE)
            .withAmount(AMOUNT)
            .build();
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithReasonText() {
    OffenseDto offense = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offense.description).isEqualTo(REASON_TEXT);
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithReasonCode() {
    OffenseDto offense = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offense.code).isEqualTo(REASON_CODE.toString());
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithAmount() {
    OffenseDto offense = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offense.amount).isEqualTo(AMOUNT);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenseDto() {
    List<Offense> manyOffenses = new ArrayList<>();
    manyOffenses.add(offense);
    manyOffenses.add(offense);

    List<OffenseDto> assembledOffenses = offenseAssembler.assembleOffenseDtos(manyOffenses);

    Truth.assertThat(assembledOffenses.size()).isEqualTo(2);
  }
}
