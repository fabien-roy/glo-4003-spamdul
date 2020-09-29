package ca.ulaval.glo4003.injection.location;

import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;

public class LocationResourceConfig {

  public PostalCodeAssembler createPostalCodeAssembler() {
    return new PostalCodeAssembler();
  }
}
