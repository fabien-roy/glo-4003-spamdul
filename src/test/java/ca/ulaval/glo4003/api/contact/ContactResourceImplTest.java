package ca.ulaval.glo4003.api.contact;

import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.contact.dto.ContactDto;
import ca.ulaval.glo4003.domain.contact.ContactNotFoundException;
import ca.ulaval.glo4003.domain.contact.ContactService;
import com.google.common.truth.Truth;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactResourceImplTest {
  private static final String CONTACT_ID = "id";

  @Mock private ContactService contactService;
  @Mock private ContactDto contactDto;

  private ContactResource contactResource;

  @Before
  public void setUp() {
    contactResource = new ContactResourceImpl(contactService);
  }

  @Test
  public void whenFindAllContacts_thenFoundContactsFromService() {
    BDDMockito.given(contactService.findAllContacts()).willReturn(Lists.newArrayList(contactDto));

    List<ContactDto> contactDtos = contactResource.getContacts();

    Truth.assertThat(contactDtos).contains(contactDto);
  }

  @Test
  public void whenFindContact_thenFoundContactFromService() {
    BDDMockito.given(contactService.findContact(CONTACT_ID)).willReturn(contactDto);

    ContactDto contactDto = contactResource.getContact(CONTACT_ID);

    Truth.assertThat(contactDto).isEqualTo(this.contactDto);
  }

  @Test
  public void whenAddContact_thenAddContactToService() {
    contactResource.addContact(contactDto);

    Mockito.verify(contactService).addContact(eq(contactDto));
  }

  @Test
  public void whenUpdateContact_thenUpdateContactInService() throws ContactNotFoundException {
    contactResource.updateContact(CONTACT_ID, contactDto);

    Mockito.verify(contactService).updateContact(eq(CONTACT_ID), eq(contactDto));
  }

  @Test
  public void whenDeleteContact_thenDeleteContactFromService() {
    contactResource.deleteContact(CONTACT_ID);

    Mockito.verify(contactService).deleteContact(eq(CONTACT_ID));
  }
}
