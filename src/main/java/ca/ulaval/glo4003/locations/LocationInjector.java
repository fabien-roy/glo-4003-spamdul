package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;

public class LocationInjector {

  public PostalCodeAssembler createPostalCodeAssembler() {
    return new PostalCodeAssembler();
  }
}