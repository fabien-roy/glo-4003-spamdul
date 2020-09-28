package ca.ulaval.glo4003.injection.contact;

import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.contact.ContactResourceImplementation;
import ca.ulaval.glo4003.domain.contact.Contact;
import ca.ulaval.glo4003.domain.contact.ContactAssembler;
import ca.ulaval.glo4003.domain.contact.ContactRepository;
import ca.ulaval.glo4003.domain.contact.ContactService;
import ca.ulaval.glo4003.infrastructure.contact.ContactFakeFactory;
import ca.ulaval.glo4003.infrastructure.contact.ContactRepositoryInMemory;
import java.util.List;

public class ContactResourceConfig {

  public ContactResource createContactResource(boolean isDev) {
    ContactRepository contactRepository = new ContactRepositoryInMemory();

    if (isDev) {
      ContactFakeFactory contactFakeFactory = new ContactFakeFactory();
      List<Contact> contacts = contactFakeFactory.createMockData();
      contacts.forEach(contactRepository::save);
    }

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImplementation(contactService);
  }
}
