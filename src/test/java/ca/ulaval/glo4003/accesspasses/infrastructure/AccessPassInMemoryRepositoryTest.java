package ca.ulaval.glo4003.accesspasses.infrastructure;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassRepository;
import org.junit.Before;
import org.junit.Test;

public class AccessPassInMemoryRepositoryTest {
  private AccessPassRepository accessPassRepository;

  private final AccessPass accessPass = anAccessPass().build();

  @Before
  public void setUp() {
    accessPassRepository = new AccessPassInMemoryRepository();
  }

  @Test
  public void whenSavingAccessPass_thenReturnAccessPassCode() {
    AccessPassCode accessPassCode = accessPassRepository.save(accessPass);

    assertThat(accessPassCode).isSameInstanceAs(accessPass.getCode());
  }

  @Test
  public void givenSavedAccessPass_whenGettingAccessPass_thenReturnAccessPass() {
    accessPassRepository.save(accessPass);

    AccessPass receivedAccessPass = accessPassRepository.get(accessPass.getCode());

    assertThat(receivedAccessPass).isEqualTo(accessPass);
  }
}
