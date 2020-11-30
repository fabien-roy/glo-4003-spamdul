package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.console.PostalSenderSystemPrint;
import ca.ulaval.glo4003.locations.console.SspSenderSystemPrint;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.locations.domain.SspSender;
import ca.ulaval.glo4003.locations.services.assemblers.PostalCodeAssembler;

public class LocationInjector {

  public PostalCodeAssembler createPostalCodeAssembler() {
    return new PostalCodeAssembler();
  }

  public PostalSender createPostalCodeSender() {
    return new PostalSenderSystemPrint();
  }

  public SspSender createSspCodeSender() {
    return new SspSenderSystemPrint();
  }
}
