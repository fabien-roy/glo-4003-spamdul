package ca.ulaval.glo4003.errors;

import ca.ulaval.glo4003.errors.api.ApplicationExceptionMapper;
import ca.ulaval.glo4003.errors.api.CatchAllExceptionMapper;
import ca.ulaval.glo4003.errors.api.ErrorResponseStatusConverter;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;

// TODO #305 : Test this
public class ErrorInjector {
  public List<ExceptionMapper> createExceptionMappers() {
    return Arrays.asList(
        new CatchAllExceptionMapper(),
        new ApplicationExceptionMapper(new ErrorResponseStatusConverter()));
  }
}
