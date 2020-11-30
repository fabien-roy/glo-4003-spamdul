package ca.ulaval.glo4003.times.services.assemblers;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class CustomDateAssemblerTest {
  private CustomDateAssembler customDateAssembler;

  private final CustomDate date = createPastDate();

  @Before
  public void setUp() {
    customDateAssembler = new CustomDateAssembler();
  }

  @Test
  public void whenAssembling_thenReturnCustomDate() {
    CustomDate customDate = customDateAssembler.assemble(date.toString());

    Truth.assertThat(customDate).isEqualTo(date);
  }

  @Test(expected = InvalidDateException.class)
  public void givenInvalidDate_whenAssembling_thenThrowInvalidDateException() {
    String invalidDate = "invalidDate";

    customDateAssembler.assemble(invalidDate);
  }
}
