package ca.ulaval.glo4003.domain.communication;

public interface EmailSender {

  void sendEmail(String emailAddress, String emailSubject, String emailContent);
}
