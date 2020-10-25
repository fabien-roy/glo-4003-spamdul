package ca.ulaval.glo4003.initiatives.assembler;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.initiatives.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.exception.InvalidInitiativeNameException;
import java.util.List;
import java.util.stream.Collectors;

public class InitiativeAssembler {
  private final MoneyAssembler moneyAssembler;

  public InitiativeAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public List<InitiativeDto> assembleMany(List<Initiative> initiatives) {
    return initiatives.stream().map(this::assemble).collect(Collectors.toList());
  }

  public Initiative assemble(AddInitiativeDto addInitiativeDto) {
    if (addInitiativeDto.name == null) throw new InvalidInitiativeNameException();

    return new Initiative(addInitiativeDto.name, moneyAssembler.assemble(addInitiativeDto.amount));
  }

  public InitiativeDto assemble(Initiative initiative) {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.code = initiative.getCode().toString();
    initiativeDto.name = initiative.getName();
    initiativeDto.allocatedAmount = initiative.getAllocatedAmount().toDouble();

    return initiativeDto;
  }
}
