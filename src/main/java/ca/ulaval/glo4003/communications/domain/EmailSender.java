package ca.ulaval.glo4003.communications.domain;

public interface EmailSender {

  void sendEmail(String emailAddress, String emailSubject, String emailContent);
}
