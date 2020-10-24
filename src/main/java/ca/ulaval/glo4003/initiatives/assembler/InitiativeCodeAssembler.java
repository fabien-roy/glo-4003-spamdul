package ca.ulaval.glo4003.initiatives.assembler;

import ca.ulaval.glo4003.initiatives.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;

public class InitiativeCodeAssembler {

  public InitiativeCode assemble(String initiativeCode) {
    return new InitiativeCode(initiativeCode);
  }

  public InitiativeCodeDto assemble(InitiativeCode initiativeCode) {
    InitiativeCodeDto initiativeCodeDto = new InitiativeCodeDto();
    initiativeCodeDto.initiativeCode = initiativeCode.toString();
    return initiativeCodeDto;
  }
}
