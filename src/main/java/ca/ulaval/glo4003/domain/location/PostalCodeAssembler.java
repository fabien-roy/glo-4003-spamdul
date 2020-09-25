package ca.ulaval.glo4003.domain.location;

import ca.ulaval.glo4003.domain.location.exception.InvalidPostalCodeException;
import java.util.regex.Pattern;

public class PostalCodeAssembler {
  private static final Pattern PATTERN = Pattern.compile("[A-Z][0-9][A-Z] [0-9][A-Z][0-9]");

  public PostalCode assemble(String postalCode) {
    if (!PATTERN.matcher(postalCode).matches()) {
      throw new InvalidPostalCodeException();
    }

    return new PostalCode(postalCode);
  }
}
