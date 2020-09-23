package ca.ulaval.glo4003.domain.user.userEnum;

import ca.ulaval.glo4003.domain.user.exception.InvalidSexAttributeException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class SexTest {
  @Test
  public void whenGettingEnumFromInvalidString_thenThrowInvalidCommunicationException() {
    String invalidSex = "invalid";

    try {
      Sex.get(invalidSex);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidSexAttributeException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenGettingEnumFromValidString_thenReturnStringAsEnum() {
    String sex = "m";
    Sex sexValue = Sex.get(sex);

    Truth.assertThat(sexValue.toString()).isEqualTo(sex);
  }
}
