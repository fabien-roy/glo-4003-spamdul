package ca.ulaval.glo4003.initiatives.services.assemblers;

import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeCodeDto;

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
