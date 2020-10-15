package ca.ulaval.glo4003.carboncredits.insfrastructure;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditBuilder.aCarbonCredit;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import org.junit.Before;
import org.junit.Test;

public class CarbonCreditRepositoryInMemoryTest {
  private CarbonCreditRepository carbonCreditRepository;
  private CarbonCredit carbonCredit;
  private CarbonCredit beforeCarbonCreditAmount;

  @Before
  public void setUp() {
    carbonCreditRepository = new CarbonCreditRepositoryInMemory();
    carbonCredit = aCarbonCredit().build();
    beforeCarbonCreditAmount = carbonCreditRepository.getAll();

    carbonCreditRepository.addCarbonCredit(carbonCredit);
  }

  @Test
  public void whenGettingCarbonCredits_thenReturnCarbonCredits() {
    CarbonCredit afterCarbonCreditAmount = carbonCreditRepository.getAll();

    assertThat(afterCarbonCreditAmount.minus(beforeCarbonCreditAmount)).isEqualTo(carbonCredit);
  }
}
