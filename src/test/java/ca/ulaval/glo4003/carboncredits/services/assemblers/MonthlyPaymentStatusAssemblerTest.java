package ca.ulaval.glo4003.carboncredits.services.assemblers;

import static ca.ulaval.glo4003.carboncredits.helpers.MonthlyPaymentStatusDtoBuilder.aMonthlyPaymentStatusDto;

import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.services.dto.MonthlyPaymentStatusDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MonthlyPaymentStatusAssemblerTest {
  private MonthlyPaymentStatusAssembler monthlyPaymentStatusAssembler;
  private MonthlyPaymentStatusDto monthlyPaymentStatusDto;

  @Before
  public void setUp() {
    monthlyPaymentStatusAssembler = new MonthlyPaymentStatusAssembler();

    monthlyPaymentStatusDto = aMonthlyPaymentStatusDto().build();
  }

  @Test
  public void whenAssembling_thenMonthlyPaymentStatusIsReturned() {
    MonthlyPaymentStatus monthlyPaymentStatus =
        monthlyPaymentStatusAssembler.assemble(monthlyPaymentStatusDto);

    Truth.assertThat(monthlyPaymentStatus).isEqualTo(MonthlyPaymentStatus.DISABLE);
  }
}
