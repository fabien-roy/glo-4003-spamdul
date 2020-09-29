package ca.ulaval.glo4003.injection.communication;

import ca.ulaval.glo4003.domain.communication.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.communication.EmailSender;
import ca.ulaval.glo4003.infrastructure.communication.EmailSenderGmailSMTP;

public class CommunicationResourceConfig {

  public EmailAddressAssembler createEmailAddressAssembler() {
    return new EmailAddressAssembler();
  }

  // TODO : Test createEmailSender
  public EmailSender createEmailSender() {
    return new EmailSenderGmailSMTP();
  }
}
