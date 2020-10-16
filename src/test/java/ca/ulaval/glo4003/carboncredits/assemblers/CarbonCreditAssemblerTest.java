package ca.ulaval.glo4003.carboncredits.assemblers;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditBuilder.aCarbonCredit;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditAssemblerTest {
  private CarbonCreditAssembler carbonCreditAssembler;

  private final CarbonCredit carbonCredit = aCarbonCredit().build();

  @Before
  public void setUp() {
    carbonCreditAssembler = new CarbonCreditAssembler();
  }

  @Test
  public void whenAssembling_thenReturnCarbonCreditDtoWithCarbonCreditAmount() {
    CarbonCreditDto carbonCreditDto = carbonCreditAssembler.assemble(carbonCredit);

    Truth.assertThat(carbonCreditDto.carbonCreditAmount).isEqualTo(carbonCredit.toDouble());
  }
}
