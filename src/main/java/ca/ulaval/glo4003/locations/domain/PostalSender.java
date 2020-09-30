package ca.ulaval.glo4003.locations.domain;

public interface PostalSender {
  void sendPostal(PostalCode postalCode, String message);
}
