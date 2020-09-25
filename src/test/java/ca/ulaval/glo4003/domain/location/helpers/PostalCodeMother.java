package ca.ulaval.glo4003.domain.location.helpers;

import ca.ulaval.glo4003.domain.location.PostalCode;
import com.github.javafaker.Faker;

public class PostalCodeMother {
  private static final String PATTERN = "[A-Z][0-9][A-Z] [0-9][A-Z][0-9]";

  public static PostalCode createPostalCode() {
    String postalCode = Faker.instance().regexify(PATTERN);
    return new PostalCode(postalCode);
  }
}
