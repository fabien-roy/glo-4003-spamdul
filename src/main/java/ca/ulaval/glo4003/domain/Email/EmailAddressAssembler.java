package ca.ulaval.glo4003.domain.Email;

import ca.ulaval.glo4003.domain.Email.exception.InvalidEmailAddressException;
import java.util.regex.Pattern;

public class EmailAddressAssembler {
  private static final Pattern PATTERN =
      Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

  public EmailAddress assemble(String emailAddress) {
    if (!PATTERN.matcher(emailAddress).matches()) {
      throw new InvalidEmailAddressException();
    }

    return new EmailAddress(emailAddress);
  }
}
