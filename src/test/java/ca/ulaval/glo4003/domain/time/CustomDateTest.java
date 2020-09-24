package ca.ulaval.glo4003.domain.time;

import com.google.common.truth.Truth;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

public class CustomDateTest {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  @Test
  public void whenConvertingToString_thenReturnsDateAsString() {
    String expectedString = "02-02-2010";
    LocalDate localDate = LocalDate.parse(expectedString, FORMATTER);
    CustomDate customDate = new CustomDate(localDate);

    String actualString = customDate.toString();

    Truth.assertThat(actualString).isEqualTo(expectedString);
  }
}
