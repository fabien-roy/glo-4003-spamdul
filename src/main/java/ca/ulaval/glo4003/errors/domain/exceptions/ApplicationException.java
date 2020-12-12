package ca.ulaval.glo4003.errors.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO #305 : Use @Override instead of this constructor?
public abstract class ApplicationException extends RuntimeException {
  private final String error;
  private final String description;
  private final ErrorCode code;

  protected ApplicationException(String error, String description, ErrorCode errorCode) {
    super(error);
    this.error = error;
    this.description = description;
    this.code = errorCode;
  }

  public String getError() {
    return error;
  }

  public String getDescription() {
    return description;
  }

  public ErrorCode getCode() {
    return code;
  }

  protected <T extends Enum<?>> String enumerateValues(Class<T> enumToEnumerate) {
    List<String> values =
        Stream.of(enumToEnumerate.getEnumConstants())
            .map(Enum::toString)
            .collect(Collectors.toList());

    return enumerateStrings(values);
  }

  // TODO #305 : This is shit.
  protected String enumerateStrings(List<String> values) {
    String enumeratedValues = "";
    for (int i = 0; i < values.size(); i++) {
      enumeratedValues = enumeratedValues.concat(values.get(i));

      if (i != values.size() - 1) {
        if (i == values.size() - 2) {
          enumeratedValues = enumeratedValues.concat(" or ");
        } else {
          enumeratedValues = enumeratedValues.concat(", ");
        }
      }
    }

    return enumeratedValues;
  }
}
