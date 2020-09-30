package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.api.offense.dto.OffenseFileDTO;
import java.util.ArrayList;
import java.util.List;

public class OffenseAssembler {
  public OffenseDto assemble(Offense offense) {
    OffenseDto offenseDto = new OffenseDto();

    offenseDto.infraction = offense.getReasonText();
    offenseDto.code = offense.getReasonCode();
    offenseDto.montant = offense.getAmount();

    return offenseDto;
  }

  public List<OffenseDto> assembleMany(List<Offense> offenses) {
    List<OffenseDto> assembledOffenses = new ArrayList<>();
    for (Offense offense : offenses) {
      assembledOffenses.add(this.assemble(offense));
    }
    return assembledOffenses;
  }

  public List<Offense> assembleManyOffense(List<OffenseFileDTO> offenses) {
    List<Offense> assembledOffenses = new ArrayList<>();
    for (OffenseFileDTO offenseFileDTO : offenses) {
      assembledOffenses.add(assembleOffense(offenseFileDTO));
    }
    return assembledOffenses;
  }

  public Offense assembleOffense(OffenseFileDTO offenseFileDTO) {
    return new Offense(offenseFileDTO.infraction, offenseFileDTO.code, offenseFileDTO.montant);
  }
}
