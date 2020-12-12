package ca.ulaval.glo4003.parkings.domain.exceptions;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import org.junit.Before;
import org.junit.Test;

public class NotFoundParkingStickerExceptionTest {
  private ApplicationException exception;

  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();

  @Before
  public void setUp() {
    exception = new NotFoundParkingStickerException(parkingStickerCode);
  }

  @Test
  public void whenGettingDescription_thenWriteParkingStickerCode() {
    String expectedDescription =
        String.format("Parking sticker with code %s was not found", parkingStickerCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
