package ca.ulaval.glo4003.infrastructure.contact;

import ca.ulaval.glo4003.domain.contact.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactFakeFactory {

  public List<Contact> createMockData() {
    List<Contact> contacts = new ArrayList<>();

    Contact jobs = new Contact("514-999-0000", "California", "Steve Jobs");
    jobs.setId("1");
    contacts.add(jobs);

    Contact balmer = new Contact("781-888-1111", "Manitoba", "Steve Balmer");
    balmer.setId("2");
    contacts.add(balmer);

    Contact franklin = new Contact("964-543-6475", "Washington", "Benjamin Franklin");
    franklin.setId("3");
    contacts.add(franklin);

    return contacts;
  }
}
