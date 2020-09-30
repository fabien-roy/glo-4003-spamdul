package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.InfractionDto;
import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import java.util.ArrayList;
import java.util.List;

public class OffenseAssembler {
  public OffenseDto assemble(Offense offense) {
    OffenseDto offenseDto = new OffenseDto();

    offenseDto.description = offense.getDescription();
    offenseDto.code = offense.getCode().toString();
    offenseDto.amount = offense.getAmount();

    return offenseDto;
  }

  public List<OffenseDto> assembleOffenseDtos(List<Offense> offenses) {
    List<OffenseDto> assembledOffenses = new ArrayList<>();
    for (Offense offense : offenses) {
      assembledOffenses.add(this.assemble(offense));
    }
    return assembledOffenses;
  }

  public List<Offense> assembleOffenses(List<InfractionDto> offenses) {
    List<Offense> assembledOffenses = new ArrayList<>();
    for (InfractionDto infractionDto : offenses) {
      assembledOffenses.add(assembleOffense(infractionDto));
    }
    return assembledOffenses;
  }

  public Offense assembleOffense(InfractionDto infractionDto) {
    return new Offense(
        infractionDto.infraction, OffenseCodes.get(infractionDto.code), infractionDto.montant);
  }
}
