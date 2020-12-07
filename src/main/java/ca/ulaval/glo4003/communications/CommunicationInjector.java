package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.smtp.SmtpEmailSender;

public class CommunicationInjector {

  public EmailAddressConverter createEmailAddressConverter() {
    return new EmailAddressConverter();
  }

  public EmailSender createEmailSender() {
    return new SmtpEmailSender();
  }
}
