package ca.ulaval.glo4003.domain.offense;

import static org.junit.Assert.*;

import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.time.TimeOfDay;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseValidationTest {
  private OffenseValidation offenseValidation;

  @Before
  public void setUp() {
    offenseValidation =
        new OffenseValidation(
            new ParkingStickerCode("a-parking-code"),
            new ParkingAreaCode("2"),
            new TimeOfDay("10:30"));
  }

  @Test
  public void
      givenOffenseValidation_whenGettingParkingStickerCode_thenParkingStickerCodeIsReturned() {
    Truth.assertThat(offenseValidation.getParkingStickerCode())
        .isEqualTo(new ParkingStickerCode("a-parking-code"));
  }

  @Test
  public void givenOffenseValidation_whenGettingParkingAreaCode_thenParkingAreaCodeIsReturned() {
    Truth.assertThat(offenseValidation.getParkingAreaCode()).isEqualTo(new ParkingAreaCode("2"));
  }

  @Test
  public void givenOffenseValidation_whenGettingTimeOfDay_thenTimeOfDayIsReturned() {
    Truth.assertThat(offenseValidation.getTimeOfDay()).isEqualTo(new TimeOfDay("10:30"));
  }
}
