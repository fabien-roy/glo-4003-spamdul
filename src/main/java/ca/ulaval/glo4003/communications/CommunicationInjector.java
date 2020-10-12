package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.smtp.EmailSenderGmailSmtp;

public class CommunicationInjector {

  public EmailAddressAssembler createEmailAddressAssembler() {
    return new EmailAddressAssembler();
  }

  // TODO : Test createEmailSender
  public EmailSender createEmailSender() {
    return new EmailSenderGmailSmtp();
  }
}
