package ca.ulaval.glo4003.initiatives.services.assemblers;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeCodeDto;
import org.junit.Before;
import org.junit.Test;

public class InitiativeCodeAssemblerTest {
  private InitiativeCodeAssembler initiativeCodeAssembler;

  private final InitiativeCode initiativeCode = createInitiativeCode();

  @Before
  public void setUp() {
    initiativeCodeAssembler = new InitiativeCodeAssembler();
  }

  @Test
  public void whenAssemblingString_thenReturnInitiativeCode() {
    InitiativeCode AssembledInitiativeCode =
        initiativeCodeAssembler.assemble(initiativeCode.toString());

    assertThat(AssembledInitiativeCode).isEqualTo(initiativeCode);
  }

  @Test
  public void whenAssemblingInitiativeCodeDto_thenReturnInitiativeCode() {
    InitiativeCodeDto AssembledInitiativeCodeDto = initiativeCodeAssembler.assemble(initiativeCode);

    assertThat(AssembledInitiativeCodeDto.initiativeCode).isEqualTo(initiativeCode.toString());
  }
}
