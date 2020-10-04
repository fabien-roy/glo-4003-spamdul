package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.offenses.api.dto.InfractionDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import java.util.ArrayList;
import java.util.List;

public class OffenseAssembler {
  public OffenseTypeDto assemble(Offense offense) {
    OffenseTypeDto offenseTypeDto = new OffenseTypeDto();

    offenseTypeDto.description = offense.getDescription();
    offenseTypeDto.code = offense.getCode().toString();
    offenseTypeDto.amount = offense.getAmount();

    return offenseTypeDto;
  }

  public List<OffenseTypeDto> assembleMany(List<Offense> offenses) {
    List<OffenseTypeDto> assembledOffenses = new ArrayList<>();
    for (Offense offense : offenses) {
      assembledOffenses.add(this.assemble(offense));
    }
    return assembledOffenses;
  }

  public List<Offense> assembleManyFromInfractionDtos(List<InfractionDto> offenses) {
    List<Offense> assembledOffenses = new ArrayList<>();
    for (InfractionDto infractionDto : offenses) {
      assembledOffenses.add(assembleFromInfractionDto(infractionDto));
    }
    return assembledOffenses;
  }

  public Offense assembleFromInfractionDto(InfractionDto infractionDto) {
    return new Offense(
        infractionDto.infraction, OffenseCodes.get(infractionDto.code), infractionDto.montant);
  }
}
