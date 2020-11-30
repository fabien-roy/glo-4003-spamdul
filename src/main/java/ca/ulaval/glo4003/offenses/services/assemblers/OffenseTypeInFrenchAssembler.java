package ca.ulaval.glo4003.offenses.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.services.dto.OffenseDtoInFrench;
import java.util.List;
import java.util.stream.Collectors;

public class OffenseTypeInFrenchAssembler {

  private final OffenseCodeAssembler offenseCodeAssembler;
  private final MoneyConverter moneyConverter;

  public OffenseTypeInFrenchAssembler(
      OffenseCodeAssembler offenseCodeAssembler, MoneyConverter moneyConverter) {
    this.offenseCodeAssembler = offenseCodeAssembler;
    this.moneyConverter = moneyConverter;
  }

  public OffenseType assemble(OffenseDtoInFrench offenseDtoInFrench) {
    OffenseCode offenseCode = offenseCodeAssembler.assemble(offenseDtoInFrench.code);
    Money amount = moneyConverter.convert(offenseDtoInFrench.montant);

    return new OffenseType(offenseDtoInFrench.infraction, offenseCode, amount);
  }

  public List<OffenseType> assembleMany(List<OffenseDtoInFrench> offensesInFrenchDto) {
    return offensesInFrenchDto.stream().map(this::assemble).collect(Collectors.toList());
  }
}
