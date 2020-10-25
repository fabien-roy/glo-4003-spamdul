package ca.ulaval.glo4003.communications.smtp;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.communications.domain.EmailPropertyHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SmtpClientTest {

  @Mock private SmtpTransportDelegator smtpTransportDelegator;
  @Mock private EmailPropertyHelper emailPropertyHelper;
  @Mock private MimeMessage mimeMessage;

  private SmtpClient smtpClient;

  private final String emailHost = createEmailHost();
  private final String emailSender = createEmailAddress().toString();
  private final String emailPassword = createEmailPassword();

  @Before
  public void setUp() {
    smtpClient = new SmtpClient(smtpTransportDelegator, emailPropertyHelper);

    when(emailPropertyHelper.getHost()).thenReturn(emailHost);
    when(emailPropertyHelper.getSender()).thenReturn(emailSender);
    when(emailPropertyHelper.getPassword()).thenReturn(emailPassword);
  }

  @Test
  public void whenCreatingMessage_thenSetFromToMessage() throws MessagingException {
    MimeMessage message = smtpClient.createMessage();

    assertThat(message.getFrom()).hasLength(1);
  }

  @Test
  public void whenSendingMessage_thenSendWithSmtpTransportDelegator() {
    smtpClient.sendMessage(mimeMessage);

    verify(smtpTransportDelegator).sendMessage(mimeMessage, emailSender, emailPassword);
  }
}
