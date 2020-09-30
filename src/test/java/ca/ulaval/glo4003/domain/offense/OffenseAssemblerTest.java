package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.api.offense.helpers.OffenseDtoBuilder.anOffenseDto;
import static ca.ulaval.glo4003.domain.offense.helpers.OffenseBuilder.anOffense;
import static ca.ulaval.glo4003.domain.offense.helpers.OffenseMother.*;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
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
  private static final String REASON_CODE = createReasonCode();
  private static final int AMOUNT = createAmount();

  private Offense offense;
  private OffenseDto offenseDto;

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
    offenseDto =
        anOffenseDto()
            .withReasonText(REASON_TEXT)
            .withReasonCode(REASON_CODE)
            .withAmount(AMOUNT)
            .build();
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithReasonText() {
    OffenseDto offenseDto = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offenseDto.reasonText).isEqualTo(REASON_TEXT);
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithReasonCode() {
    OffenseDto offenseDto = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offenseDto.reasonCode).isEqualTo(REASON_CODE);
  }

  @Test
  public void whenAssembling_thenReturnOffenseDtoWithAmount() {
    OffenseDto offenseDto = offenseAssembler.assemble(this.offense);

    Truth.assertThat(offenseDto.amount).isEqualTo(AMOUNT);
  }

  @Test
  public void whenAssemblingMany_thenReturnManyOffenseDto() {
    List<Offense> manyOffenses = new ArrayList<>();
    manyOffenses.add(offense);
    manyOffenses.add(offense);

    List<OffenseDto> assembledOffenseDtos = offenseAssembler.assembleMany(manyOffenses);

    Truth.assertThat(assembledOffenseDtos.size()).isEqualTo(2);
  }
}
