package ca.ulaval.glo4003.injection;

import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.injection.account.AccountResourceConfig;
import ca.ulaval.glo4003.injection.contact.ContactResourceConfig;
import ca.ulaval.glo4003.injection.time.TimeResourceConfig;
import ca.ulaval.glo4003.injection.user.UserResourceConfig;

public class ApplicationResourceConfig {

  private static final boolean IS_DEV = true;

  private final AccountResourceConfig accountResourceConfig;
  private final ContactResourceConfig contactResourceConfig;
  private final TimeResourceConfig timeResourceConfig;
  private final UserResourceConfig userResourceConfig;

  public ApplicationResourceConfig() {
    accountResourceConfig = new AccountResourceConfig();
    contactResourceConfig = new ContactResourceConfig();
    userResourceConfig = new UserResourceConfig();
    timeResourceConfig = new TimeResourceConfig();
  }

  public ContactResource createContactResource() {
    return contactResourceConfig.createContactResource(IS_DEV);
  }

  public UserResource createUserResource() {
    return userResourceConfig.createUserResource(
        accountResourceConfig.getAccountRepository(),
        accountResourceConfig.createAccountFactory(),
        accountResourceConfig.createAccountIdAssembler(),
        timeResourceConfig.createCustomDateAssembler());
  }
}
