package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import java.util.List;
import java.util.stream.Collectors;

public class InfractionAssembler {

  private final OffenseCodeAssembler offenseCodeAssembler;

  public InfractionAssembler(OffenseCodeAssembler offenseCodeAssembler) {
    this.offenseCodeAssembler = offenseCodeAssembler;
  }

  public OffenseType assemble(InfractionDto infraction) {
    OffenseCode offenseCode = offenseCodeAssembler.assemble(infraction.code);

    return new OffenseType(infraction.infraction, offenseCode, infraction.montant);
  }

  public List<OffenseType> assembleMany(List<InfractionDto> infractions) {
    return infractions.stream().map(this::assemble).collect(Collectors.toList());
  }
}
