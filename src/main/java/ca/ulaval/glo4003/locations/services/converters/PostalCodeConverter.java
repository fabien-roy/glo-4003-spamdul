package ca.ulaval.glo4003.locations.services.converters;

import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.domain.exceptions.InvalidPostalCodeException;
import java.util.regex.Pattern;

public class PostalCodeConverter {
  private static final Pattern PATTERN = Pattern.compile("[A-Z][0-9][A-Z] [0-9][A-Z][0-9]");
  private static final String FORMAT = "XAX AXA, where X is an letter and A is a number";

  public PostalCode convert(String postalCode) {
    if (!PATTERN.matcher(postalCode).matches()) {
      throw new InvalidPostalCodeException(FORMAT);
    }

    return new PostalCode(postalCode);
  }
}
