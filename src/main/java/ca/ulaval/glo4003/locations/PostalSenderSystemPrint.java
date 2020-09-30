package ca.ulaval.glo4003.locations;

import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.domain.PostalSender;

public class PostalSenderSystemPrint implements PostalSender {
  private static final String POSTAL_MESSAGE = "Envoie a la boîte postal %s :";

  @Override
  public void sendPostal(PostalCode postalCode, String message) {
    System.out.println(String.format(POSTAL_MESSAGE, postalCode.toString()));
    System.out.println(message);
  }
}
