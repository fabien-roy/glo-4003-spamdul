package ca.ulaval.glo4003.communications.helpers;

import ca.ulaval.glo4003.communications.domain.EmailAddress;
import com.github.javafaker.Faker;

public class EmailAddressMother {
  public static EmailAddress createEmailAddress() {
    String address = Faker.instance().internet().emailAddress();
    return new EmailAddress(address);
  }
}
