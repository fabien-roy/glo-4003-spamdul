package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseDtoInFrench;
import java.util.List;
import java.util.stream.Collectors;

public class OffenseTypeInFrenchAssembler {

  private final OffenseCodeAssembler offenseCodeAssembler;
  private final MoneyAssembler moneyAssembler;

  public OffenseTypeInFrenchAssembler(
      OffenseCodeAssembler offenseCodeAssembler, MoneyAssembler moneyAssembler) {
    this.offenseCodeAssembler = offenseCodeAssembler;
    this.moneyAssembler = moneyAssembler;
  }

  public OffenseType assemble(OffenseDtoInFrench offenseDtoInFrench) {
    OffenseCode offenseCode = offenseCodeAssembler.assemble(offenseDtoInFrench.code);
    Money amount = moneyAssembler.assemble(offenseDtoInFrench.montant);

    return new OffenseType(offenseDtoInFrench.infraction, offenseCode, amount);
  }

  public List<OffenseType> assembleMany(List<OffenseDtoInFrench> offensesInFrenchDto) {
    return offensesInFrenchDto.stream().map(this::assemble).collect(Collectors.toList());
  }
}
