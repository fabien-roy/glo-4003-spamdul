package ca.ulaval.glo4003.access.assembler;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;

import ca.ulaval.glo4003.access.api.dto.AccessPassCodeDto;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.exceptions.InvalidAccessPassCodeException;
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
