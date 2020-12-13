package ca.ulaval.glo4003.communications;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.domain.PostalSender;
import ca.ulaval.glo4003.communications.domain.SspSender;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.services.converters.PostalCodeConverter;
import org.junit.Before;
import org.junit.Test;

public class CommunicationInjectorTest {

  private CommunicationInjector communicationInjector;

  @Before
  public void setUp() {
    communicationInjector = new CommunicationInjector();
  }

  @Test
  public void whenCreatingEmailAddressConverter_thenReturnIt() {
    EmailAddressConverter emailAddressConverter =
        communicationInjector.createEmailAddressConverter();

    assertThat(emailAddressConverter).isNotNull();
  }

  @Test
  public void whenCreatingEmailSender_thenReturnIt() {
    EmailSender emailSender = communicationInjector.createEmailSender();

    assertThat(emailSender).isNotNull();
  }

  @Test
  public void whenCreatingPostalCodeConverter_thenReturnIt() {
    PostalCodeConverter postalCodeConverter = communicationInjector.createPostalCodeConverter();

    assertThat(postalCodeConverter).isNotNull();
  }

  @Test
  public void whenCreatingPostalSender_thenReturnIt() {
    PostalSender postalSender = communicationInjector.createPostalCodeSender();

    assertThat(postalSender).isNotNull();
  }

  @Test
  public void whenCreatingSspSender_thenReturnIt() {
    SspSender sspSender = communicationInjector.createSspSender();

    assertThat(sspSender).isNotNull();
  }
}
