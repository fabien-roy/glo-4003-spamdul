package ca.ulaval.glo4003.domain.contact;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.contact.dto.ContactDto;
import ca.ulaval.glo4003.domain.contact.exception.ContactNotFoundException;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
  private static final String CONTACT_ID = "id";

  @Mock private Contact contact;
  @Mock private ContactDto contactDto;
  @Mock private ContactRepository contactRepository;
  @Mock private ContactAssembler contactAssembler;

  private ContactService contactService;

  @Before
  public void setUp() {
    contactService = new ContactService(contactRepository, contactAssembler);
  }

  @Test
  public void givenContactsInRepository_whenFindAllContacts_thenReturnThose() {
    BDDMockito.given(contactRepository.findAll()).willReturn(Collections.singletonList(contact));
    BDDMockito.given(contactAssembler.create(contact)).willReturn(contactDto);

    List<ContactDto> contactDtos = contactService.findAllContacts();

    Truth.assertThat(contactDtos).contains(contactDto);
    Mockito.verify(contactRepository).findAll();
    Mockito.verify(contactAssembler).create(eq(contact));
  }

  @Test
  public void givenContactsInRepository_whenFindContact_thenReturnThem() {
    BDDMockito.given(contactRepository.findById(CONTACT_ID)).willReturn(contact);
    BDDMockito.given(contactAssembler.create(contact)).willReturn(contactDto);

    ContactDto contactDto = contactService.findContact(CONTACT_ID);

    Truth.assertThat(contactDto).isEqualTo(this.contactDto);
    Mockito.verify(contactRepository).findById(CONTACT_ID);
    Mockito.verify(contactAssembler).create(eq(contact));
  }

  @Test
  public void whenAddContact_thenAddThem() {
    BDDMockito.given(contactAssembler.create(contactDto)).willReturn(contact);

    contactService.addContact(contactDto);

    Mockito.verify(contact).setId(anyString());
    Mockito.verify(contactRepository).save(eq(contact));
    Mockito.verify(contactAssembler).create(eq(contactDto));
  }

  @Test
  public void whenUpdateContact_thenUpdateThem() throws ContactNotFoundException {
    BDDMockito.given(contactAssembler.create(contactDto)).willReturn(contact);

    contactService.updateContact(CONTACT_ID, contactDto);

    Mockito.verify(contact).setId(eq(CONTACT_ID));
    Mockito.verify(contactRepository).update(eq(contact));
    Mockito.verify(contactAssembler).create(eq(contactDto));
  }

  @Test
  public void whenRemoveContact_thenRemoveThem() {
    contactService.deleteContact(CONTACT_ID);

    Mockito.verify(contactRepository).remove(eq(CONTACT_ID));
  }
}
