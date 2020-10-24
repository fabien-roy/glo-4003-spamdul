package ca.ulaval.glo4003.initiative.assembler;

import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;

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
