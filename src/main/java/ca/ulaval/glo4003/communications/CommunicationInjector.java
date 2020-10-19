package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.assemblers.EmailAddressAssembler;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.smtp.GmailSmtpClient;
import ca.ulaval.glo4003.communications.smtp.GmailSmtpEmailClient;
import ca.ulaval.glo4003.communications.smtp.SmtpTransportDelegator;

public class CommunicationInjector {

  public EmailAddressAssembler createEmailAddressAssembler() {
    return new EmailAddressAssembler();
  }

  public EmailSender createEmailSender() {
    SmtpTransportDelegator smtpTransportDelegator = new SmtpTransportDelegator();
    GmailSmtpClient gmailSmtpClient = new GmailSmtpClient(smtpTransportDelegator);
    return new GmailSmtpEmailClient(gmailSmtpClient);
  }
}
