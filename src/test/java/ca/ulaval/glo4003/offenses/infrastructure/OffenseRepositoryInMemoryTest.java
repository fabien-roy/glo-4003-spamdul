package ca.ulaval.glo4003.offenses.infrastructure;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseRepository;
import ca.ulaval.glo4003.offenses.exceptions.OffenseNotFoundException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OffenseRepositoryInMemoryTest {

  private static final String A_REASON = "Not enough chips";
  private static final OffenseCodes A_CODE = OffenseCodes.VIG_01;
  private static final OffenseCodes ANOTHER_CODE = OffenseCodes.TEMPS_01;
  private static final double AN_AMOUNT = 420;

  private OffenseRepository offenseRepository;

  @Before
  public void setup() {
    offenseRepository = new OffenseRepositoryInMemory();
  }

  @Test
  public void whenSaving_thenAddOffenseToMap() {
    Offense offense = new Offense(A_REASON, A_CODE, AN_AMOUNT);

    offenseRepository.save(offense);

    assertThat(offenseRepository.findByCode(A_CODE)).isEqualTo(offense);
  }

  @Test(expected = OffenseNotFoundException.class)
  public void givenCodeWithNoOffense_whenFindingByCode_thenThrowException() {
    offenseRepository.findByCode(OffenseCodes.ZONE_03);
  }

  @Test
  public void whenGetAll_thenReturnAll() {
    Offense anOffense = new Offense(A_REASON, A_CODE, AN_AMOUNT);
    Offense anotherOffense = new Offense(A_REASON, ANOTHER_CODE, AN_AMOUNT);
    offenseRepository.save(anOffense);
    offenseRepository.save(anotherOffense);

    List<Offense> storedOffenses = offenseRepository.getAll();

    assertThat(storedOffenses).contains(anOffense);
    assertThat(storedOffenses).contains(anotherOffense);
  }
}
