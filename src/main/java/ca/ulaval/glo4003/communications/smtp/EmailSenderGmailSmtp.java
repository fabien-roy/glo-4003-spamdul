package ca.ulaval.glo4003.communications.smtp;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO : Test EmailSenderGmailSmtp
public class EmailSenderGmailSmtp implements EmailSender {
  // TODO : Change this to english
  private static final String PARKING_STICKER_CREATION_SUBJECT = "Votre vignette SPAMD-UL";
  private static final String PARKING_STICKER_CREATION_TEXT =
      "Votre code de vignette SPAMD-UL est %s";

  private final GmailSmtpClient gmailSmtpClient;

  public EmailSenderGmailSmtp(GmailSmtpClient gmailSmtpClient) {
    this.gmailSmtpClient = gmailSmtpClient;
  }

  @Override
  public void sendEmail(String emailAddress, String emailSubject, String emailContent) {
    Session session = gmailSmtpClient.getSession();
    MimeMessage message = new MimeMessage(session);

    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
      message.setSubject(emailSubject);
      message.setText(emailContent);
    } catch (MessagingException exception) {
      throw new EmailSendingFailedException();
    }

    gmailSmtpClient.sendMessage(message);
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
