package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.DayOfWeek;
import org.junit.Test;

public class AccessPassTest {

  private static final DayOfWeek VALID_DAY = DayOfWeek.MONDAY;
  private static final DayOfWeek INVALID_DAY = DayOfWeek.FRIDAY;

  private final AccessPass accessPass = anAccessPass().withValidDay(VALID_DAY).build();

  @Test
  public void givenAccessDayOnValidDay_whenValidatingAccessDay_thenReturnTrue() {
    boolean isAccessDayValid = accessPass.validateAccessDay(VALID_DAY);

    assertThat(isAccessDayValid).isTrue();
  }

  @Test
  public void givenAccessDayNotOnValidDay_whenValidatingAccessDay_thenReturnFalse() {
    boolean isAccessDayValid = accessPass.validateAccessDay(INVALID_DAY);

    assertThat(isAccessDayValid).isFalse();
  }
}
