package ca.ulaval.glo4003.initiative.assembler;

import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import java.util.List;
import java.util.stream.Collectors;

public class InitiativeAssembler {
  public List<InitiativeDto> assemble(List<Initiative> initiatives) {
    return initiatives.stream().map(this::assemble).collect(Collectors.toList());
  }

  public InitiativeDto assemble(Initiative initiative) {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.initiativeCode = initiative.getInitiativeCode().toString();
    initiativeDto.initiativeName = initiative.getInitiativeName();
    initiativeDto.allocatedAmount = initiative.getAllocatedAmount().toDouble();

    return initiativeDto;
  }
}
