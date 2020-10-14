package ca.ulaval.glo4003.communications.smtp;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO : Test EmailSenderGmailSmtp
public class EmailSenderGmailSmtp implements EmailSender {
  private static final String EMAIL_SENDER = "SPAMDUL.eq4@gmail.com";
  private static final String EMAIL_PASSWORD = "InsquisitionsEspagnolesEquipeQuatre";
  private static final String PARKING_STICKER_CREATION_SUBJECT = "Votre vignette SPAMD-UL";
  private static final String PARKING_STICKER_CREATION_TEXT =
      "Votre code de vignette SPAMD-UL est %s";

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

  @Override
  public void listenParkingStickerCreated(ParkingSticker parkingSticker) {
    if (parkingSticker.getReceptionMethod().equals(ReceptionMethod.EMAIL)) {
      sendEmail(
          parkingSticker.getEmailAddress().toString(),
          PARKING_STICKER_CREATION_SUBJECT,
          String.format(PARKING_STICKER_CREATION_TEXT, parkingSticker.getCode().toString()));
    }
  }
}
