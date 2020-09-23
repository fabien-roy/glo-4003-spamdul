package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.user.exception.InvalidSexAttributeException;
import com.google.common.truth.Truth;
import org.junit.Test;

// TODO : Enums should not be tested. We should only tests via assemblers or factories.
public class SexTest {
  @Test(expected = InvalidSexAttributeException.class)
  public void whenGettingEnumFromInvalidString_thenThrowInvalidSexAttributeException() {
    String invalidSex = "invalid";

    Sex.get(invalidSex);
  }

  @Test
  public void whenGettingEnumFromValidString_thenReturnStringAsEnum() {
    String sex = "m";
    Sex sexValue = Sex.get(sex);

    Truth.assertThat(sexValue.toString()).isEqualTo(sex);
  }
}
