package ca.ulaval.glo4003.locations.services.converters;

import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.domain.exceptions.InvalidPostalCodeException;
import java.util.regex.Pattern;

public class PostalCodeConverter {
  private static final Pattern PATTERN = Pattern.compile("[A-Z][0-9][A-Z] [0-9][A-Z][0-9]");

  public PostalCode convert(String postalCode) {
    if (!PATTERN.matcher(postalCode).matches()) {
      throw new InvalidPostalCodeException();
    }

    return new PostalCode(postalCode);
  }
}
