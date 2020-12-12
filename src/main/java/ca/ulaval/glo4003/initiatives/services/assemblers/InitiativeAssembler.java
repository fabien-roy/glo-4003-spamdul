package ca.ulaval.glo4003.initiatives.services.assemblers;

import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.exceptions.InvalidInitiativeNameException;
import ca.ulaval.glo4003.initiatives.services.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiatives.services.dto.InitiativeDto;
import java.util.List;
import java.util.stream.Collectors;

public class InitiativeAssembler {
  private final MoneyConverter moneyConverter;

  public InitiativeAssembler() {
    this(new MoneyConverter());
  }

  public InitiativeAssembler(MoneyConverter moneyConverter) {
    this.moneyConverter = moneyConverter;
  }

  public List<InitiativeDto> assembleMany(List<Initiative> initiatives) {
    return initiatives.stream().map(this::assemble).collect(Collectors.toList());
  }

  public Initiative assemble(AddInitiativeDto addInitiativeDto) {
    if (addInitiativeDto.name == null) throw new InvalidInitiativeNameException();

    return new Initiative(addInitiativeDto.name, moneyConverter.convert(addInitiativeDto.amount));
  }

  public InitiativeDto assemble(Initiative initiative) {
    InitiativeDto initiativeDto = new InitiativeDto();
    initiativeDto.code = initiative.getCode().toString();
    initiativeDto.name = initiative.getName();
    initiativeDto.allocatedAmount = initiative.getAllocatedAmount().toDouble();

    return initiativeDto;
  }
}
