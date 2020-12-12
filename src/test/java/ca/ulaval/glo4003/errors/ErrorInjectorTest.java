package ca.ulaval.glo4003.errors;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.api.ApplicationExceptionMapper;
import ca.ulaval.glo4003.errors.api.CatchAllExceptionMapper;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;
import org.junit.Before;
import org.junit.Test;

public class ErrorInjectorTest {

  private ErrorInjector errorInjector;

  @Before
  public void setUp() {
    errorInjector = new ErrorInjector();
  }

  @Test
  public void whenCreatingExceptionMappers_thenCreateCatchAllExceptionMapper() {
    List<ExceptionMapper> exceptionMappers = errorInjector.createExceptionMappers();

    assertThat(exceptionMappers.get(0)).isInstanceOf(CatchAllExceptionMapper.class);
  }

  @Test
  public void whenCreatingExceptionMappers_thenCreateApplicationExceptionMapper() {
    List<ExceptionMapper> exceptionMappers = errorInjector.createExceptionMappers();

    assertThat(exceptionMappers.get(1)).isInstanceOf(ApplicationExceptionMapper.class);
  }
}
