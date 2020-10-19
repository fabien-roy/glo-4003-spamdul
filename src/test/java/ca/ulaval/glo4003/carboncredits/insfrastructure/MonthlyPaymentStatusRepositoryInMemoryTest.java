package ca.ulaval.glo4003.carboncredits.insfrastructure;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;
import org.junit.Before;
import org.junit.Test;

public class MonthlyPaymentStatusRepositoryInMemoryTest {
  private MonthlyPaymentStatusRepository monthlyPaymentStatusRepository;

  @Before
  public void setUp() {
    monthlyPaymentStatusRepository = new MonthlyPaymentStatusRepositoryInMemory();
  }

  @Test
  public void givenMonthlyPaymentStatus_whenSaving_thenMonthlyPaymentStatusIsSaved() {
    MonthlyPaymentStatus monthlyPaymentStatus = MonthlyPaymentStatus.ENABLE;

    monthlyPaymentStatusRepository.save(monthlyPaymentStatus);

    assertThat(monthlyPaymentStatusRepository.equals(monthlyPaymentStatus.toString()));
  }
}
