package ca.ulaval.glo4003.injection.contact;

import ca.ulaval.glo4003.api.contact.ContactResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class ContactResourceConfigTest {

  private ContactResourceConfig contactResourceConfig;

  @Before
  public void setUp() {
    contactResourceConfig = new ContactResourceConfig();
  }

  @Test
  public void whenCreatingContactResource_thenReturnIt() {
    ContactResource contactResource = contactResourceConfig.createContactResource(false);

    Truth.assertThat(contactResource).isNotNull();
  }
}
