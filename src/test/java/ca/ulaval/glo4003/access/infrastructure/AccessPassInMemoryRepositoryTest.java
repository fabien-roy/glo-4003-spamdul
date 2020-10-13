package ca.ulaval.glo4003.access.infrastructure;

import static ca.ulaval.glo4003.access.helpers.AccessPassBuilder.anAccessPass;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import com.google.common.truth.Truth;
import org.junit.Test;

public class AccessPassInMemoryRepositoryTest {
  private AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private AccessPass accessPass = anAccessPass().build();

  @Test
  public void whenSavingParkingSticker_thenReturnAccessPassCode() {
    AccessPassCode accessPassCode = accessPassInMemoryRepository.save(accessPass);

    Truth.assertThat(accessPassCode).isEqualTo(accessPass.getCode());
  }
}
