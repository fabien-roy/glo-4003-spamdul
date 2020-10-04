package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import java.util.List;
import java.util.stream.Collectors;

public class InfractionAssembler {

  public OffenseType assemble(InfractionDto infraction) {
    return new OffenseType(
        infraction.infraction, OffenseCodes.get(infraction.code), infraction.montant);
  }

  public List<OffenseType> assembleMany(List<InfractionDto> infractions) {
    return infractions.stream().map(this::assemble).collect(Collectors.toList());
  }
}
