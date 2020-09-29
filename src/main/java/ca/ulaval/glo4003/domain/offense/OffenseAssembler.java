package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import java.util.ArrayList;
import java.util.List;

public class OffenseAssembler {
  public OffenseDto assemble(Offense offense) {
    OffenseDto offenseDto = new OffenseDto();

    offenseDto.reasonText = offense.getReasonText();
    offenseDto.reasonCode = offense.getReasonCode();
    offenseDto.amount = offense.getAmount();

    return offenseDto;
  }

  public List<OffenseDto> assembleMany(List<Offense> offenses) {
    List<OffenseDto> assembledOffenses = new ArrayList<>();
    for (Offense offense : offenses) {
      assembledOffenses.add(this.assemble(offense));
    }
    return assembledOffenses;
  }
}
