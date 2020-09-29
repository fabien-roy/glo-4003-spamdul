package ca.ulaval.glo4003.injection.email;

import ca.ulaval.glo4003.domain.Email.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.Email.EmailSender;
import ca.ulaval.glo4003.infrastructure.email.EmailSenderGmailSMTP;

public class EmailResourceConfig {

  public EmailAddressAssembler emailAddressAssembler() {
    return new EmailAddressAssembler();
  }

  public EmailSender emailSender() {
    return new EmailSenderGmailSMTP();
  }
}
