package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import java.util.List;
import java.util.stream.Collectors;

public class InfractionAssembler {

  private final OffenseCodeAssembler offenseCodeAssembler;
  private final MoneyAssembler moneyAssembler;

  public InfractionAssembler(
      OffenseCodeAssembler offenseCodeAssembler, MoneyAssembler moneyAssembler) {
    this.offenseCodeAssembler = offenseCodeAssembler;
    this.moneyAssembler = moneyAssembler;
  }

  public OffenseType assemble(InfractionDto infraction) {
    OffenseCode offenseCode = offenseCodeAssembler.assemble(infraction.code);
    Money amount = moneyAssembler.assemble(infraction.montant);

    return new OffenseType(infraction.infraction, offenseCode, amount);
  }

  public List<OffenseType> assembleMany(List<InfractionDto> infractions) {
    return infractions.stream().map(this::assemble).collect(Collectors.toList());
  }
}
