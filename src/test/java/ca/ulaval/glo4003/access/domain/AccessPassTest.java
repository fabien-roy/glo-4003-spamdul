package ca.ulaval.glo4003.access.domain;

import static ca.ulaval.glo4003.access.helper.AccessPassBuilder.anAccessPass;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.Days;
import org.junit.Test;

public class AccessPassTest {

  private static final Days VALID_DAY = Days.MONDAY;
  private static final Days INVALID_DAY = Days.FRIDAY;

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
