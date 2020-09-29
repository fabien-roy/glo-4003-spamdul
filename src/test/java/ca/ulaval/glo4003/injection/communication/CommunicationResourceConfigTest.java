package ca.ulaval.glo4003.injection.communication;

import ca.ulaval.glo4003.domain.communication.EmailAddressAssembler;
import ca.ulaval.glo4003.domain.communication.EmailSender;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class CommunicationResourceConfigTest {

  private CommunicationResourceConfig communicationResourceConfig;

  @Before
  public void setUp() {
    communicationResourceConfig = new CommunicationResourceConfig();
  }

  @Test
  public void whenCreatingEmailAddressAssembler_thenReturnIt() {
    EmailAddressAssembler emailAddressAssembler =
        communicationResourceConfig.createEmailAddressAssembler();

    Truth.assertThat(emailAddressAssembler).isNotNull();
  }

  @Test
  public void whenCreatingEmailSender_thenReturnIt() {
    EmailSender emailSender = communicationResourceConfig.createEmailSender();

    Truth.assertThat(emailSender).isNotNull();
  }
}
