package ca.ulaval.glo4003.times.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import java.util.List;
import java.util.stream.Collectors;

public class NotFoundSemesterException extends ApplicationException {
  private static final String ERROR = "Semester not found";
  private static final String DESCRIPTION = "Semester should be one of %s";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final List<SemesterCode> semesterCodes;

  public NotFoundSemesterException(List<SemesterCode> semesterCodes) {
    super(ERROR, DESCRIPTION, CODE);
    this.semesterCodes = semesterCodes;
  }

  @Override
  public String getDescription() {
    List<String> parkingCodes =
        semesterCodes.stream().map(SemesterCode::toString).collect(Collectors.toList());
    return String.format(DESCRIPTION, enumerateStrings(parkingCodes));
  }
}
