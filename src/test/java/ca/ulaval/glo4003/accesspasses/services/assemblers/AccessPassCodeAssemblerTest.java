package ca.ulaval.glo4003.accesspasses.services.assemblers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassCodeException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassCodeDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccessPassCodeAssemblerTest {
  private AccessPassCodeAssembler accessPassCodeAssembler;

  private final AccessPassCode accessPassCode = createAccessPassCode();

  @Before
  public void setUp() {
    accessPassCodeAssembler = new AccessPassCodeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnParkingCode() {
    AccessPassCode assembledAccessPassCode =
        accessPassCodeAssembler.assemble(accessPassCode.toString());

    Truth.assertThat(assembledAccessPassCode).isEqualTo(accessPassCode);
  }

  @Test(expected = InvalidAccessPassCodeException.class)
  public void givenNullAccessPassCode_whenAssembling_thenThrowInvalidAccessPassCodeException() {
    accessPassCodeAssembler.assemble((String) null);
  }

  @Test
  public void whenAssemblingDto_thenReturnParkingCodeDto() {
    AccessPassCodeDto accessPassCodeDto = accessPassCodeAssembler.assemble(accessPassCode);

    Truth.assertThat(accessPassCodeDto.accessPassCode).isEqualTo(accessPassCode.toString());
  }
}
