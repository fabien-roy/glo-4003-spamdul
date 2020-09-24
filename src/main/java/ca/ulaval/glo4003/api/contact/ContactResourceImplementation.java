package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.contact.dto.ContactDto;
import ca.ulaval.glo4003.domain.contact.ContactService;
import ca.ulaval.glo4003.domain.contact.exception.ContactNotFoundException;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ContactResourceImplementation implements ContactResource {
  private final ContactService contactService;

  public ContactResourceImplementation(ContactService contactService) {
    this.contactService = contactService;
  }

  @Override
  public List<ContactDto> getContacts() {
    return contactService.findAllContacts();
  }

  @Override
  public ContactDto getContact(String id) {
    return contactService.findContact(id);
  }

  @Override
  public void addContact(ContactDto contactDto) {
    contactService.addContact(contactDto);
  }

  @Override
  public void updateContact(String id, ContactDto contactDto) {
    try {
      contactService.updateContact(id, contactDto);
    } catch (ContactNotFoundException e) {
      throw new WebApplicationException(
          Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build());
    }
  }

  @Override
  public void deleteContact(String id) {
    contactService.deleteContact(id);
  }
}