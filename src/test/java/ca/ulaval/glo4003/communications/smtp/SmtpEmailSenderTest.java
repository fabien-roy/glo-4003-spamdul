package ca.ulaval.glo4003.communications.smtp;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static ca.ulaval.glo4003.communications.helpers.EmailMother.*;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.communications.domain.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
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
public class SmtpEmailSenderTest {

  @Mock private SmtpClient smtpClient;
  @Mock private MimeMessage message;

  private SmtpEmailSender smtpEmailSender;

  private final String emailAddress = createEmailAddress().toString();
  private final String emailSubject = createEmailSubject();
  private final String emailContent = createEmailContent();

  @Before
  public void setUp() {
    smtpEmailSender = new SmtpEmailSender(smtpClient);

    when(smtpClient.createMessage()).thenReturn(message);
  }

  @Test
  public void whenSendingMessage_thenAddRecipientToMessage() throws MessagingException {
    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
  }

  @Test
  public void whenSendingMessage_thenSetSubjectToMessage() throws MessagingException {
    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).setSubject(emailSubject);
  }

  @Test
  public void whenSendingMessage_thenSetTextToMessage() throws MessagingException {
    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);

    verify(message).setText(emailContent);
  }

  @Test
  public void whenSendingMessage_thenSendMessageWithClient() {
    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);

    verify(smtpClient).sendMessage(message);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailAddress_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class)
        .when(message)
        .addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailSubject_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class).when(message).setSubject(emailSubject);

    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailContent_whenSendingMessage_thenThrowEmailSendingFailedException()
      throws MessagingException {
    doThrow(MessagingException.class).when(message).setText(emailContent);

    smtpEmailSender.sendEmail(emailAddress, emailSubject, emailContent);
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningForParkingStickerCreation_thenSendMessageWithClient() {
    ParkingSticker parkingSticker =
        aParkingSticker().withReceptionMethod(ReceptionMethod.EMAIL).build();

    smtpEmailSender.listenParkingStickerCreated(parkingSticker);

    verify(smtpClient).sendMessage(message);
  }

  @Test
  public void
      givenPostalReceptionMethod_whenListeningForParkingStickerCreation_thenDoNotSendMessageWithClient() {
    ParkingSticker parkingSticker =
        aParkingSticker().withReceptionMethod(ReceptionMethod.POSTAL).build();

    smtpEmailSender.listenParkingStickerCreated(parkingSticker);

    verify(smtpClient, never()).sendMessage(message);
  }

  @Test
  public void
      givenEmailReceptionMethod_whenListeningForAccessPassCreation_thenSendMessageWithClient() {
    AccessPass accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.EMAIL).build();

    smtpEmailSender.listenAccessPassCreated(accessPass);

    verify(smtpClient).sendMessage(message);
  }

  @Test
  public void
      givenPostalReceptionMethod_whenListeningForAccessPassCreation_thenDoNotSendMessageWithClient() {
    AccessPass accessPass = anAccessPass().withReceptionMethod(ReceptionMethod.POSTAL).build();

    smtpEmailSender.listenAccessPassCreated(accessPass);

    verify(smtpClient, never()).sendMessage(message);
  }
}
