package ca.ulaval.glo4003.accesspasses.services.converters;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccessPassCodeConverterTest {
  private AccessPassCodeConverter accessPassCodeConverter;

  private final AccessPassCode accessPassCode = createAccessPassCode();

  @Before
  public void setUp() {
    accessPassCodeConverter = new AccessPassCodeConverter();
  }

  @Test
  public void whenConverting_thenReturnParkingCode() {
    AccessPassCode assembledAccessPassCode =
        accessPassCodeConverter.convert(accessPassCode.toString());

    Truth.assertThat(assembledAccessPassCode).isEqualTo(accessPassCode);
  }

  @Test(expected = InvalidAccessPassCodeException.class)
  public void givenNullAccessPassCode_whenConverting_thenThrowInvalidAccessPassCodeException() {
    accessPassCodeConverter.convert((String) null);
  }

  @Test
  public void whenConvertingDto_thenReturnParkingCodeDto() {
    AccessPassCodeDto accessPassCodeDto = accessPassCodeConverter.convert(accessPassCode);

    Truth.assertThat(accessPassCodeDto.accessPassCode).isEqualTo(accessPassCode.toString());
  }
}
