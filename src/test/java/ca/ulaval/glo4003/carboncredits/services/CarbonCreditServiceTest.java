package ca.ulaval.glo4003.carboncredits.services;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditDtoBuilder.aCarbonCreditDto;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditAssembler;
import ca.ulaval.glo4003.carboncredits.assemblers.CarbonCreditMonthlyPaymentStatusAssembler;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditServiceTest {
  @Mock CarbonCreditRepository carbonCreditRepository;
  @Mock CarbonCreditAssembler carbonCreditAssembler;
  @Mock CarbonCreditMonthlyPaymentStatusAssembler carbonCreditMonthlyPaymentStatusAssembler;
  @Mock MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;

  private CarbonCreditService carbonCreditService;

  private final CarbonCreditDto carbonCreditDto = aCarbonCreditDto().build();

  @Before
  public void setUp() {
    carbonCreditService =
        new CarbonCreditService(
            carbonCreditRepository,
            carbonCreditAssembler,
            carbonCreditMonthlyPaymentStatusAssembler,
            monthlyPaymentStatusRepository);

    when(carbonCreditAssembler.assemble(any()))
        .thenReturn(carbonCreditDto); // TODO : Do not use any()
  }

  // TODO : Test modifyCarbonCreditMonthlyPaymentStatus

  @Test
  public void whenGettingCarbonCredits_thenReturnCarbonCreditAmount() {
    Truth.assertThat(carbonCreditService.getCarbonCredits()).isSameInstanceAs(carbonCreditDto);
  }
}
