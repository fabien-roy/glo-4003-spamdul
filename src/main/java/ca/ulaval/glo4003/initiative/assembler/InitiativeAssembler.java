package ca.ulaval.glo4003.initiative.assembler;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import java.util.List;
import java.util.stream.Collectors;

public class InitiativeAssembler {

  private MoneyAssembler moneyAssembler;

  public InitiativeAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public List<InitiativeDto> assemble(List<Initiative> initiatives) {
    return initiatives.stream().map(this::assemble).collect(Collectors.toList());
  }

  public Initiative assemble(AddInitiativeDto addInitiativeDto) {
    return new Initiative(addInitiativeDto.name, moneyAssembler.assemble(addInitiativeDto.amount));
  }

  public InitiativeDto assemble(Initiative initiative) {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.code = initiative.getInitiativeCode().toString();
    initiativeDto.name = initiative.getInitiativeName();
    initiativeDto.allocatedAmount = initiative.getAllocatedAmount().toDouble();

    return initiativeDto;
  }
}
