package ca.ulaval.glo4003.communications.services.converters;

import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.domain.exceptions.InvalidEmailAddressException;
import java.util.regex.Pattern;

public class EmailAddressConverter {
  private static final Pattern PATTERN = Pattern.compile("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}");

  public EmailAddress convert(String emailAddress) {
    if (!PATTERN.matcher(emailAddress).matches()) {
      throw new InvalidEmailAddressException();
    }

    return new EmailAddress(emailAddress);
  }
}
