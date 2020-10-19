package ca.ulaval.glo4003.communications.smtp;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GmailSmtpClientTest {

  @Mock private SmtpTransportDelegator smtpTransportDelegator;
  @Mock private MimeMessage mimeMessage;

  private GmailSmtpClient gmailSmtpClient;

  @Before
  public void setUp() {
    gmailSmtpClient = new GmailSmtpClient(smtpTransportDelegator);
  }

  @Test
  public void whenCreatingMessage_thenSetFromToMessage() throws MessagingException {
    MimeMessage message = gmailSmtpClient.createMessage();

    assertThat(message.getFrom()).hasLength(1);
  }

  @Test
  public void whenSendingMessage_thenSendWithSmtpTransportDelegator() {
    gmailSmtpClient.sendMessage(mimeMessage);

    // TODO : Remove usage of anyString() by using fake env vars
    verify(smtpTransportDelegator).sendMessage(eq(mimeMessage), anyString(), anyString());
  }
}
