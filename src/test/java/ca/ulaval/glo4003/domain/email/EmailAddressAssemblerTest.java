package ca.ulaval.glo4003.domain.email;

import static ca.ulaval.glo4003.domain.email.helpers.EmailAddressMother.createEmailAddress;

import ca.ulaval.glo4003.domain.Email.EmailAddress;
import ca.ulaval.glo4003.domain.Email.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.Email.exception.InvalidEmailAddressException;
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
    EmailAddress emailAddress = emailAddressAssembler.assemble(EMAIL_ADDRESS.toString());

    Truth.assertThat(emailAddress).isEqualTo(EMAIL_ADDRESS);
  }

  @Test(expected = InvalidEmailAddressException.class)
  public void givenInvalidEmailAddress_whenAssembling_thenThrowInvalidEmailAddressException() {
    String invalidEmailAddress = "invalidEmailAddress";

    emailAddressAssembler.assemble(invalidEmailAddress);
  }
}
