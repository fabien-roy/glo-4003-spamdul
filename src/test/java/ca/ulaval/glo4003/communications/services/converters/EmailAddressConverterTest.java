package ca.ulaval.glo4003.communications.services.converters;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailAddress;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.domain.exceptions.InvalidEmailAddressException;
import org.junit.Before;
import org.junit.Test;

public class EmailAddressConverterTest {
  private static final EmailAddress EMAIL_ADDRESS = createEmailAddress();

  private EmailAddressConverter emailAddressConverter;

  @Before
  public void setUp() {
    emailAddressConverter = new EmailAddressConverter();
  }

  @Test
  public void whenConverting_thenReturnEmailAddress() {
    EmailAddress postalCode = emailAddressConverter.convert(EMAIL_ADDRESS.toString());

    assertThat(postalCode).isEqualTo(EMAIL_ADDRESS);
  }

  @Test(expected = InvalidEmailAddressException.class)
  public void givenInvalidEmailAddress_whenConverting_thenThrowInvalidEmailAddressException() {
    String invalidEmailAddress = "invalidEmailAddress";

    emailAddressConverter.convert(invalidEmailAddress);
  }
}
