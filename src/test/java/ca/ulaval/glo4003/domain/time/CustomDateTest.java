package ca.ulaval.glo4003.domain.time;

import static ca.ulaval.glo4003.domain.time.helpers.CustomDateMother.createFutureDate;
import static ca.ulaval.glo4003.domain.time.helpers.CustomDateMother.createPastDate;

import com.google.common.truth.Truth;
import java.time.LocalDate;
import org.junit.Test;

public class CustomDateTest {
  @Test
  public void givenPastDate_whenAskingIfFuture_thenReturnFalse() {
    CustomDate customDate = createPastDate();

    Truth.assertThat(customDate.isFuture()).isFalse();
  }

  @Test
  public void givenFutureDate_whenAskingIfFuture_thenReturnTrue() {
    CustomDate customDate = createFutureDate();

    Truth.assertThat(customDate.isFuture()).isTrue();
  }

  @Test
  public void whenConvertingToString_thenReturnsDateAsString() {
    String expectedString = "02-02-2010";
    LocalDate localDate = LocalDate.of(2010, 2, 2);
    CustomDate customDate = new CustomDate(localDate);

    String actualString = customDate.toString();

    Truth.assertThat(actualString).isEqualTo(expectedString);
  }
}
