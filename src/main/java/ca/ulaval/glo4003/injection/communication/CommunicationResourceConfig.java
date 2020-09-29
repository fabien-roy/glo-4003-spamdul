package ca.ulaval.glo4003.injection.communication;

import ca.ulaval.glo4003.domain.communication.EmailAddressAssembler;

public class CommunicationResourceConfig {

  public EmailAddressAssembler createEmailAddressAssembler() {
    return new EmailAddressAssembler();
  }
}
