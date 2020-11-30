package ca.ulaval.glo4003.communications;

import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class CommunicationInjectorTest {

  private CommunicationInjector communicationInjector;

  @Before
  public void setUp() {
    communicationInjector = new CommunicationInjector();
  }

  @Test
  public void whenCreatingEmailAddressAssembler_thenReturnIt() {
    EmailAddressConverter emailAddressConverter =
        communicationInjector.createEmailAddressAssembler();

    Truth.assertThat(emailAddressConverter).isNotNull();
  }

  @Test
  public void whenCreatingEmailSender_thenReturnIt() {
    EmailSender emailSender = communicationInjector.createEmailSender();

    Truth.assertThat(emailSender).isNotNull();
  }
}
