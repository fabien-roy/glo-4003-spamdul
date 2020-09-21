package ca.ulaval.glo4003.domain.user.userEnum;

import ca.ulaval.glo4003.domain.user.exception.InvalidCommunicationMethodAttributeException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class CommunicationMethodTest {

  @Test
  public void whenGettingEnumFromInvalidString_thenThrowInvalidCommunicationException() {
    String invalidCommunicationMethod = "invalid";

    try {
      CommunicationMethod.get(invalidCommunicationMethod);
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidCommunicationMethodAttributeException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenGettingEnumFromValidString_thenReturnStringAsEnum() {
    String communicationMethod = "postal";
    CommunicationMethod communicationMethodValue = CommunicationMethod.get(communicationMethod);

    Truth.assertThat(communicationMethodValue.toString()).isEqualTo(communicationMethod);
  }
}
