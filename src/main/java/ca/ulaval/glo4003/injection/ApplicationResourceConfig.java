package ca.ulaval.glo4003.injection;

import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.injection.contact.ContactResourceConfig;

public class ApplicationResourceConfig {

  private static final boolean IS_DEV = true;

  private final ContactResourceConfig contactResourceConfig;

  public ApplicationResourceConfig() {
    contactResourceConfig = new ContactResourceConfig();
  }

  public ContactResource createContactResource() {
    return contactResourceConfig.createContactResource(IS_DEV);
  }
}
