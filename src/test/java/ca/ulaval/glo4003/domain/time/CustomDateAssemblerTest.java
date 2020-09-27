package ca.ulaval.glo4003.domain.time;

import static ca.ulaval.glo4003.domain.time.helpers.CustomDateMother.createPastDate;

import ca.ulaval.glo4003.domain.time.exception.InvalidDateException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomDateAssemblerTest {
  private static final CustomDate DATE = createPastDate();

  private CustomDateAssembler customDateAssembler;

  @Before
  public void setUp() {
    customDateAssembler = new CustomDateAssembler();
  }

  @Test
  public void whenAssembling_thenReturnCustomDate() {
    CustomDate customDate = customDateAssembler.assemble(DATE.toString());

    Truth.assertThat(customDate).isEqualTo(DATE);
  }

  @Test(expected = InvalidDateException.class)
  public void givenInvalidDate_whenAssembling_thenThrowInvalidDateException() {
    String invalidDate = "invalidDate";

    customDateAssembler.assemble(invalidDate);
  }
}
