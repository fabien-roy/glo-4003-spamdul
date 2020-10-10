package ca.ulaval.glo4003.access.infrastructure;

import static ca.ulaval.glo4003.access.helper.AccessPassBuilder.anAccessPass;

import ca.ulaval.glo4003.access.domain.AccessPass;
import com.google.common.truth.Truth;
import org.junit.Test;

public class AccessPassInMemoryRepositoryTest {
  private AccessPassInMemoryRepository accessPassInMemoryRepository =
      new AccessPassInMemoryRepository();
  private AccessPass accessPass = anAccessPass().build();

  @Test
  public void whenSavingParkingSticker_thenParkingStickerCanBeFound() {
    accessPassInMemoryRepository.save(accessPass);

    AccessPass accessPassInMemory =
        accessPassInMemoryRepository.getAccessPasses().get(accessPass.getAccessPassCode());

    Truth.assertThat(accessPassInMemory).isSameInstanceAs(accessPass);
  }
}
