package ca.ulaval.glo4003.locations.services.converters;

import static ca.ulaval.glo4003.locations.helpers.PostalCodeMother.createPostalCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.domain.exceptions.InvalidPostalCodeException;
import org.junit.Before;
import org.junit.Test;

public class PostalCodeConverterTest {
  private static final PostalCode POSTAL_CODE = createPostalCode();

  private PostalCodeConverter postalCodeConverter;

  @Before
  public void setUp() {
    postalCodeConverter = new PostalCodeConverter();
  }

  @Test
  public void whenConverting_thenReturnPostalCode() {
    PostalCode postalCode = postalCodeConverter.convert(POSTAL_CODE.toString());

    assertThat(postalCode).isEqualTo(POSTAL_CODE);
  }

  @Test(expected = InvalidPostalCodeException.class)
  public void givenInvalidPostalCode_whenConverting_thenThrowInvalidPostalCodeException() {
    String invalidPostalCode = "invalidPostalCode";

    postalCodeConverter.convert(invalidPostalCode);
  }
}
