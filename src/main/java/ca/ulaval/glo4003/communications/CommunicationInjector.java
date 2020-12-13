package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.console.PostalSenderSystemPrint;
import ca.ulaval.glo4003.communications.console.SspSenderSystemPrint;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.domain.PostalSender;
import ca.ulaval.glo4003.communications.domain.SspSender;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.communications.smtp.SmtpEmailSender;

public class CommunicationInjector {

  public EmailAddressConverter createEmailAddressConverter() {
    return new EmailAddressConverter();
  }

  public EmailSender createEmailSender() {
    return new SmtpEmailSender();
  }

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
