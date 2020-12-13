package ca.ulaval.glo4003.communications.smtp;

import ca.ulaval.glo4003.communications.domain.EmailPropertyHelper;
import ca.ulaval.glo4003.communications.domain.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.communications.filesystem.EmailPropertyFileHelper;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpClient {
  private final SmtpTransportDelegator smtpTransportDelegator;
  private final EmailPropertyHelper propertyHelper;

  public SmtpClient() {
    this(new SmtpTransportDelegator(), new EmailPropertyFileHelper());
  }

  public SmtpClient(
      SmtpTransportDelegator smtpTransportDelegator, EmailPropertyHelper propertyHelper) {
    this.smtpTransportDelegator = smtpTransportDelegator;
    this.propertyHelper = propertyHelper;
  }

  public MimeMessage createMessage() {
    Session session = getSession();
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(propertyHelper.getSender()));
    } catch (MessagingException exception) {
      throw new EmailSendingFailedException();
    }

    return message;
  }

  public void sendMessage(MimeMessage message) {
    smtpTransportDelegator.sendMessage(
        message, propertyHelper.getSender(), propertyHelper.getPassword());
  }

  private Session getSession() {
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", propertyHelper.getHost());
    properties.setProperty("mail.smtp.port", "587");
    properties.setProperty("mail.smtp.starttls.enable", "true");

    return Session.getInstance(properties);
  }
}
