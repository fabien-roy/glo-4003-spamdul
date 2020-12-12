package ca.ulaval.glo4003.schedulers.domain.exceptions;

public class CannotBuildSchedulerException extends SchedulerException {
  private static final String ERROR = "Cannot build scheduler";
  private static final String DESCRIPTION = "Something went wrong when building scheduler";

  public CannotBuildSchedulerException() {
    super(ERROR, DESCRIPTION);
  }
}
