package ca.ulaval.glo4003.domain.communication;

import static ca.ulaval.glo4003.domain.communication.helpers.EmailAddressMother.createEmailAddress;

import ca.ulaval.glo4003.domain.communication.exception.InvalidEmailAddressException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class EmailAddressAssemblerTest {
  private static final EmailAddress EMAIL_ADDRESS = createEmailAddress();

  private EmailAddressAssembler emailAddressAssembler;

  @Before
  public void setUp() {
    emailAddressAssembler = new EmailAddressAssembler();
  }

  @Test
  public void whenAssembling_thenReturnEmailAddress() {
    EmailAddress postalCode = emailAddressAssembler.assemble(EMAIL_ADDRESS.toString());

    Truth.assertThat(postalCode).isEqualTo(EMAIL_ADDRESS);
  }

  // TODO : A parametrized test would allow us to test many invalid email addresses, but JUnit 4.
  @Test(expected = InvalidEmailAddressException.class)
  public void givenInvalidEmailAddress_whenAssembling_thenThrowInvalidEmailAddressException() {
    String invalidEmailAddress = "invalidEmailAddress";

    emailAddressAssembler.assemble(invalidEmailAddress);
  }
}
