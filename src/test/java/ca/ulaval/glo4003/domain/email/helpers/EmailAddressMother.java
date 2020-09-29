package ca.ulaval.glo4003.domain.email.helpers;

import ca.ulaval.glo4003.domain.Email.EmailAddress;

public class EmailAddressMother {
  public static EmailAddress createEmailAddress() {
    return new EmailAddress("generic.email@ulaval.ca");
  }
}
