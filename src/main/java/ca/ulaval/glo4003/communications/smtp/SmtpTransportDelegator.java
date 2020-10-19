package ca.ulaval.glo4003.communications.smtp;

import ca.ulaval.glo4003.communications.exceptions.EmailSendingFailedException;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class SmtpTransportDelegator {
  public void sendMessage(MimeMessage message, String emailSender, String emailPassword) {
    try {
      Transport.send(message, emailSender, emailPassword);
    } catch (MessagingException exception) {
      throw new EmailSendingFailedException();
    }
  }
}
