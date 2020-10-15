package ca.ulaval.glo4003.communications.assemblers;

import static ca.ulaval.glo4003.communications.helpers.EmailAddressMother.createEmailAddress;

import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.exceptions.InvalidEmailAddressException;
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

  @Test(expected = InvalidEmailAddressException.class)
  public void givenInvalidEmailAddress_whenAssembling_thenThrowInvalidEmailAddressException() {
    String invalidEmailAddress = "invalidEmailAddress";

    emailAddressAssembler.assemble(invalidEmailAddress);
  }
}
