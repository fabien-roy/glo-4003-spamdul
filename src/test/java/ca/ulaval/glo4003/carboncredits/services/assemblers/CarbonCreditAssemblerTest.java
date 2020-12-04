package ca.ulaval.glo4003.carboncredits.services.assemblers;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMother.createCarbonCredit;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.services.dto.CarbonCreditDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditAssemblerTest {
  private CarbonCreditAssembler carbonCreditAssembler;

  private final CarbonCredit carbonCredit = createCarbonCredit();

  @Before
  public void setUp() {
    carbonCreditAssembler = new CarbonCreditAssembler();
  }

  @Test
  public void whenAssembling_thenReturnCarbonCreditDtoWithCarbonCreditAmount() {
    CarbonCreditDto carbonCreditDto = carbonCreditAssembler.assemble(carbonCredit);

    Truth.assertThat(carbonCreditDto.carbonCredits).isEqualTo(carbonCredit.toDouble());
  }
}
