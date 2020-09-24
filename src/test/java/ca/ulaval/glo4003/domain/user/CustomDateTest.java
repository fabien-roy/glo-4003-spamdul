package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.time.CustomDate;
import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class CustomDateTest {
  @Test
  public void whenCreatingCustomInvalidDate_thenTrowInvalidBirthDateException() {
    try {
      new CustomDate("0000-02");
      Truth.assertThat(false);
    } catch (Exception exception) {
      if (exception instanceof InvalidBirthDateException) {
        Truth.assertThat(true);
      }
    }
  }

  @Test
  public void whenCreatingCustomValidDate_thenReturnsCustomDate() {
    String date = "02-02-2010";
    CustomDate customDate = new CustomDate(date);

    Truth.assertThat(customDate.toString()).isEqualTo(date);
  }
}
