package ca.ulaval.glo4003.domain.communication;

import ca.ulaval.glo4003.domain.communication.exception.InvalidEmailAddressException;
import java.util.regex.Pattern;

public class EmailAddressAssembler {
  private static final Pattern PATTERN = Pattern.compile("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}");

  public EmailAddress assemble(String emailAddress) {
    if (!PATTERN.matcher(emailAddress).matches()) {
      throw new InvalidEmailAddressException();
    }

    return new EmailAddress(emailAddress);
  }
}
