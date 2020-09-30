package ca.ulaval.glo4003.communications.smtp;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.exceptions.EmailSendingFailedException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderGmailSMTP implements EmailSender {
  private static final String EMAIL_SENDER = "SPAMDUL.eq4@gmail.com";
  private static final String EMAIL_PASSWORD = "InsquisitionsEspagnolesEquipeQuatre";

  @Override
  public void sendEmail(String emailAddress, String emailSubject, String emailContent) {
    String host = "smtp.gmail.com";

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", "587");
    properties.setProperty("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(properties);

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(EMAIL_SENDER));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
      message.setSubject(emailSubject);
      message.setText(emailContent);

      Transport.send(message, EMAIL_SENDER, EMAIL_PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
      throw new EmailSendingFailedException();
    }
  }
}
