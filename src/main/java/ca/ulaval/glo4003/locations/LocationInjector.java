package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.assemblers.PostalCodeAssembler;
import ca.ulaval.glo4003.locations.console.PostalSenderSystemPrint;
import ca.ulaval.glo4003.locations.domain.PostalSender;

public class LocationInjector {

  public PostalCodeAssembler createPostalCodeAssembler() {
    return new PostalCodeAssembler();
  }

  public PostalSender createPostalCodeSender() {
    return new PostalSenderSystemPrint();
  }
}
