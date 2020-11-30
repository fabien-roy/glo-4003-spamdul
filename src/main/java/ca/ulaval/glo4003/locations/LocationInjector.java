package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.console.PostalSenderSystemPrint;
import ca.ulaval.glo4003.locations.console.SspSenderSystemPrint;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.locations.domain.SspSender;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;

public class LocationInjector {

  public PostalCodeConverter createPostalCodeConverter() {
    return new PostalCodeConverter();
  }

  public PostalSender createPostalCodeSender() {
    return new PostalSenderSystemPrint();
  }

  public SspSender createSspSender() {
    return new SspSenderSystemPrint();
  }
}
