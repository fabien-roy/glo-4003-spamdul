package ca.ulaval.glo4003.domain.communication;

import java.util.regex.Pattern;

public class EmailAddressAssembler {
  private static final Pattern PATTERN = Pattern.compile("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}");

  public EmailAddress assemble(String emailAddress) {
    // TODO : EmailAddressAssembler.assembler
    return null;
  }
}
