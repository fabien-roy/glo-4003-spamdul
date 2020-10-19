package ca.ulaval.glo4003.communications.smtp;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.*;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.communications.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GmailSmtpEmailClientTest {

  @Mock private GmailSmtpClient gmailSmtpClient;
  @Mock private MimeMessage message;

  private GmailSmtpEmailClient gmailSmtpEmailClient;

  private final String emailAddress = createEmailAddress().toString();
  private final String emailSubject = createEmailSubject();
  private final String emailContent = createEmailContent();

  @Before
  public void setUp() {
    gmailSmtpEmailClient = new GmailSmtpEmailClient(gmailSmtpClient);

    when(gmailSmtpClient.createMessage()).thenReturn(message);
  }

  @Test
  public void whenSendingMessage_thenAddRecipientToMessage() throws MessagingException {
    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
  }

  @Test
  public void whenSendingMessage_thenSetSubjectToMessage() throws MessagingException {
    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).setSubject(emailSubject);
  }

  @Test
  public void whenSendingMessage_thenSetTextToMessage() throws MessagingException {
    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).setText(emailContent);
  }

  @Test
  public void whenSendingMessage_thenSendMessageWithClient() {
    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);

    verify(gmailSmtpClient).sendMessage(message);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailAddress_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class)
        .when(message)
        .addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailSubject_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class).when(message).setSubject(emailSubject);

    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailContent_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class).when(message).setText(emailContent);

    gmailSmtpEmailClient.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningForParkingStickerCreation_thenSendMessageWithClient() {
    ParkingSticker parkingSticker =
        aParkingSticker().withReceptionMethod(ReceptionMethod.EMAIL).build();

    gmailSmtpEmailClient.listenParkingStickerCreated(parkingSticker);

    verify(gmailSmtpClient).sendMessage(message);
  }

  @Test
  public void
      givenPostalReceptionMethod_whenListeningForParkingStickerCreation_thenDoNotSendMessageWithClient() {
    ParkingSticker parkingSticker =
        aParkingSticker().withReceptionMethod(ReceptionMethod.POSTAL).build();

    gmailSmtpEmailClient.listenParkingStickerCreated(parkingSticker);

    verify(gmailSmtpClient, never()).sendMessage(message);
  }
}
