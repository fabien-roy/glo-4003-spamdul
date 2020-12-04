package ca.ulaval.glo4003.initiatives.services.assemblers;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;

import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeCodeDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class InitiativeCodeAssemblerTest {
  private InitiativeCodeAssembler initiativeCodeAssembler;
  private InitiativeCode INITIATIVE_CODE = createInitiativeCode();

  @Before
  public void setUp() {
    initiativeCodeAssembler = new InitiativeCodeAssembler();
  }

  @Test
  public void whenAssemblingString_thenReturnInitiativeCode() {
    InitiativeCode AssembledInitiativeCode =
        initiativeCodeAssembler.assemble(INITIATIVE_CODE.toString());

    Truth.assertThat(AssembledInitiativeCode).isEqualTo(INITIATIVE_CODE);
  }

  @Test
  public void whenAssemblingInitiativeCodeDto_thenReturnInitiativeCode() {
    InitiativeCodeDto AssembledInitiativeCodeDto =
        initiativeCodeAssembler.assemble(INITIATIVE_CODE);

    Truth.assertThat(AssembledInitiativeCodeDto.initiativeCode)
        .isEqualTo(INITIATIVE_CODE.toString());
  }
}
