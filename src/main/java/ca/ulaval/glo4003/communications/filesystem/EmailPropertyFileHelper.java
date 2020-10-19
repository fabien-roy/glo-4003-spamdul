package ca.ulaval.glo4003.communications.filesystem;

import ca.ulaval.glo4003.communications.domain.EmailPropertyHelper;
import ca.ulaval.glo4003.files.filesystem.PropertyFileReader;
import java.util.Properties;

public class EmailPropertyFileHelper implements EmailPropertyHelper {

  private Properties emailProperties;

  private static final String EMAIL_PROPERTIES_FILE_PATH = "data/emailSmtp.properties";
  private static final String HOST_PROPERTY = "SMTP_HOST";
  private static final String SENDER_PROPERTY = "SMTP_SENDER";
  private static final String PASSWORD_PROPERTY = "SMTP_PASSWORD";

  private final PropertyFileReader fileReader;

  public EmailPropertyFileHelper(PropertyFileReader fileReader) {
    this.fileReader = fileReader;
  }

  @Override
  public String getHost() {
    return getProperty(HOST_PROPERTY);
  }

  @Override
  public String getSender() {
    return getProperty(SENDER_PROPERTY);
  }

  @Override
  public String getPassword() {
    return getProperty(PASSWORD_PROPERTY);
  }

  private Properties getProperties() {
    return fileReader.readFile(EMAIL_PROPERTIES_FILE_PATH);
  }

  private String getProperty(String property) {
    String envProperty = System.getenv(property);
    return envProperty == null ? getFileProperty(property) : envProperty;
  }

  private String getFileProperty(String property) {
    if (emailProperties == null) emailProperties = getProperties();
    return emailProperties.getProperty(property);
  }
}
