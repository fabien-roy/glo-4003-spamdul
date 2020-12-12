package ca.ulaval.glo4003.communications.smtp;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailAddress;
import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailPassword;

import ca.ulaval.glo4003.communications.domain.exceptions.EmailSendingFailedException;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SmtpTransportDelegatorTest {

  @Mock private MimeMessage message;

  private SmtpTransportDelegator smtpTransportDelegator;

  private final String emailSender = createEmailAddress().toString();
  private final String emailPassword = createEmailPassword();

  @Before
  public void setUp() {
    smtpTransportDelegator = new SmtpTransportDelegator();
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidMessage_whenSendingMessage_thenThrowEmailSendingFailedException() {
    MimeMessage invalidMessage = null;

    smtpTransportDelegator.sendMessage(invalidMessage, emailSender, emailPassword);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailSender_whenSendingMessage_thenThrowEmailSendingFailedException() {
    String invalidEmailSender = null;

    smtpTransportDelegator.sendMessage(message, invalidEmailSender, emailPassword);
  }

  @Test(expected = EmailSendingFailedException.class)
  public void givenInvalidEmailPassword_whenSendingMessage_thenThrowEmailSendingFailedException() {
    String invalidEmailPassword = null;

    smtpTransportDelegator.sendMessage(message, emailSender, invalidEmailPassword);
  }
}
