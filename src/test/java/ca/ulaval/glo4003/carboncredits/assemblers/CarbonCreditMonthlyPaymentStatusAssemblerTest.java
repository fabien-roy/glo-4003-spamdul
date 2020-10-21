package ca.ulaval.glo4003.carboncredits.assemblers;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMonthlyPaymentStatusDtoBuilder.aCarbonCreditMonthlyPaymentStatusDto;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditMonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditMonthlyPaymentStatusAssemblerTest {
  private CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler;
  private CarbonCreditMonthlyPaymentStatusDto carbonCreditMonthlyPaymentStatusDto;

  @Before
  public void setUp() {
    carbonCreditMonthlyPaymentStatusAssembler = new CarbonCreditMonthlyPaymentStatusAssembler();

    carbonCreditMonthlyPaymentStatusDto = aCarbonCreditMonthlyPaymentStatusDto().build();
  }

  @Test
  public void whenAssembling_thenMonthlyPaymentStatusIsReturned() {
    MonthlyPaymentStatus monthlyPaymentStatus =
        carbonCreditMonthlyPaymentStatusAssembler.assemble(carbonCreditMonthlyPaymentStatusDto);

    Truth.assertThat(monthlyPaymentStatus).isEqualTo(MonthlyPaymentStatus.DISABLE);
  }
}
