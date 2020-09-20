package ca.ulaval.glo4003.infrastructure.contact;

import ca.ulaval.glo4003.domain.contact.Contact;
import ca.ulaval.glo4003.domain.contact.ContactNotFoundException;
import com.google.common.truth.Truth;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryInMemoryTest {
  private static final String CONTACT_ID = "id";

  @Mock private Contact contact;
  @Mock private Contact updatedContact;

  private ContactRepositoryInMemory contactRepositoryInMemory;

  @Before
  public void setUp() {
    contactRepositoryInMemory = new ContactRepositoryInMemory();
    BDDMockito.given(contact.getId()).willReturn(CONTACT_ID);
    BDDMockito.given(updatedContact.getId()).willReturn(CONTACT_ID);
  }

  @Test
  public void givenContact_whenFindAll_thenReturnContactInMemory() {
    contactRepositoryInMemory.save(contact);

    List<Contact> contacts = contactRepositoryInMemory.findAll();

    Truth.assertThat(contacts).contains(contact);
  }

  @Test
  public void givenContact_whenUpdate_thenUpdateContactInMemory() throws ContactNotFoundException {
    contactRepositoryInMemory.save(contact);
    contactRepositoryInMemory.update(updatedContact);

    List<Contact> contacts = contactRepositoryInMemory.findAll();

    Truth.assertThat(contacts).contains(updatedContact);
    Truth.assertThat(contacts).doesNotContain(contact);
  }

  @Test
  public void givenContact_whenRemove_thenRemoveContactFromMemory() {
    contactRepositoryInMemory.save(contact);
    contactRepositoryInMemory.remove(contact.getId());

    List<Contact> contacts = contactRepositoryInMemory.findAll();

    Truth.assertThat(contacts).doesNotContain(contact);
  }
}
