package ca.ulaval.glo4003.profits.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.profits.api.dto.ProfitsDto;

public class ProfitsAssembler {

  public ProfitsDto assemble(Money total) {
    ProfitsDto profitsDto = new ProfitsDto();
    profitsDto.profits = total.toDouble();
    return profitsDto;
  }
}
