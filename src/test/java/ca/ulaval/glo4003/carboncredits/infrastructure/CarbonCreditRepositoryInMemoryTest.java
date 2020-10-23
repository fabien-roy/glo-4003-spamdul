package ca.ulaval.glo4003.carboncredits.infrastructure;

import static ca.ulaval.glo4003.carboncredits.helpers.CarbonCreditMother.createCarbonCredit;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.domain.CarbonCreditRepository;
import org.junit.Before;
import org.junit.Test;

public class CarbonCreditRepositoryInMemoryTest {
  private CarbonCreditRepository carbonCreditRepository;
  private CarbonCredit carbonCredit = createCarbonCredit();
  private CarbonCredit addedCarbonCreditAmount;

  @Before
  public void setUp() {
    carbonCreditRepository = new CarbonCreditRepositoryInMemory();
  }

  @Test
  public void givenCarbonCredits_whenGettingCarbonCredits_thenReturnCarbonCredits() {
    carbonCreditRepository.add(carbonCredit);
    addedCarbonCreditAmount = carbonCreditRepository.get();

    assertThat(addedCarbonCreditAmount).isEqualTo(carbonCredit);
  }
}
