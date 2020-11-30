package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.domain.EmailPropertyHelper;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.filesystem.EmailPropertyFileHelper;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.smtp.SmtpClient;
import ca.ulaval.glo4003.communications.smtp.SmtpEmailSender;
import ca.ulaval.glo4003.communications.smtp.SmtpTransportDelegator;
import ca.ulaval.glo4003.files.filesystem.PropertyFileReader;

public class CommunicationInjector {

  public EmailAddressConverter createEmailAddressAssembler() {
    return new EmailAddressConverter();
  }

  public EmailSender createEmailSender() {
    SmtpTransportDelegator smtpTransportDelegator = new SmtpTransportDelegator();
    PropertyFileReader propertyFileReader = new PropertyFileReader();
    EmailPropertyHelper emailPropertyHelper = new EmailPropertyFileHelper(propertyFileReader);
    SmtpClient smtpClient = new SmtpClient(smtpTransportDelegator, emailPropertyHelper);
    return new SmtpEmailSender(smtpClient);
  }
}
