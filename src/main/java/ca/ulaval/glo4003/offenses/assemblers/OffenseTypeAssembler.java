package ca.ulaval.glo4003.offenses.assemblers;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.domain.Offense;
import java.util.List;
import java.util.stream.Collectors;

public class OffenseTypeAssembler {
  public OffenseTypeDto assemble(Offense offense) {
    OffenseTypeDto offenseTypeDto = new OffenseTypeDto();

    offenseTypeDto.description = offense.getDescription();
    offenseTypeDto.code = offense.getCode().toString();
    offenseTypeDto.amount = offense.getAmount();

    return offenseTypeDto;
  }

  public List<OffenseTypeDto> assembleMany(List<Offense> offenses) {
    return offenses.stream().map(this::assemble).collect(Collectors.toList());
  }
}
