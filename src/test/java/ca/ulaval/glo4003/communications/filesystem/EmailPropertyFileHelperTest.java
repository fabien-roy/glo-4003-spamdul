package ca.ulaval.glo4003.communications.filesystem;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.files.filesystem.PropertyFileReader;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// Cases with env vars are not tested, they would require too many weird hacks
@RunWith(MockitoJUnitRunner.class)
public class EmailPropertyFileHelperTest {

  @Mock PropertyFileReader propertyFileReader;

  private EmailPropertyFileHelper emailPropertyFileHelper;

  private final String smtpHost = createEmailHost();
  private final String smtpSender = createEmailAddress().toString();
  private final String smtpPassword = createEmailPassword();

  @Before
  public void setUp() {
    emailPropertyFileHelper = new EmailPropertyFileHelper(propertyFileReader);

    when(propertyFileReader.readFile("data/emailSmtp.properties")).thenReturn(getFakeProperties());
  }

  @Test
  public void whenGettingHost_thenReturnPropertyForHost() {
    String receivedSmtpHost = emailPropertyFileHelper.getHost();

    assertThat(receivedSmtpHost).isSameInstanceAs(smtpHost);
  }

  @Test
  public void whenGettingSender_thenReturnPropertyForSender() {
    String receivedSmtpSender = emailPropertyFileHelper.getSender();

    assertThat(receivedSmtpSender).isSameInstanceAs(smtpSender);
  }

  @Test
  public void whenGettingPassword_thenReturnPropertyForPassword() {
    String receivedSmtpPassword = emailPropertyFileHelper.getPassword();

    assertThat(receivedSmtpPassword).isSameInstanceAs(smtpPassword);
  }

  private Properties getFakeProperties() {
    Properties properties = new Properties();
    properties.setProperty("SMTP_HOST", smtpHost);
    properties.setProperty("SMTP_SENDER", smtpSender);
    properties.setProperty("SMTP_PASSWORD", smtpPassword);
    return properties;
  }
}
