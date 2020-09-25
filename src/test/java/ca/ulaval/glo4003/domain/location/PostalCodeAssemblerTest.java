package ca.ulaval.glo4003.domain.location;

import static ca.ulaval.glo4003.domain.location.helpers.PostalCodeMother.createPostalCode;

import ca.ulaval.glo4003.domain.location.exception.InvalidPostalCodeException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class PostalCodeAssemblerTest {
  private static final PostalCode POSTAL_CODE = createPostalCode();

  private PostalCodeAssembler postalCodeAssembler;

  @Before
  public void setUp() {
    postalCodeAssembler = new PostalCodeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnPostalCode() {
    PostalCode postalCode = postalCodeAssembler.assemble(POSTAL_CODE.toString());

    Truth.assertThat(postalCode).isEqualTo(POSTAL_CODE);
  }

  // TODO : A parametrized test would allow us to test many invalid postal codes, but JUnit 4.
  @Test(expected = InvalidPostalCodeException.class)
  public void givenInvalidPostalCode_whenAssembling_thenThrowInvalidPostalCodeException() {
    String invalidPostalCode = "invalidPostalCode";

    postalCodeAssembler.assemble(invalidPostalCode);
  }
}
