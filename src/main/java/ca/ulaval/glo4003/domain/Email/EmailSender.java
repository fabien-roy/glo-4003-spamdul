package ca.ulaval.glo4003.domain.Email;

public interface EmailSender {

  void sendEmail(String emailAddress, String emailSubject, String emailContent);
}
