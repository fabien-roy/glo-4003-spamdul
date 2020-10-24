package ca.ulaval.glo4003.profits.assemblers;

import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitByConsumptionTypeAssembler {

  public List<ProfitsByConsumptionTypeDto> assembleMany(
      List<ProfitByConsumptionType> profitByConsumptionTypes) {
    return profitByConsumptionTypes.stream().map(this::assemble).collect(Collectors.toList());
  }

  private ProfitsByConsumptionTypeDto assemble(ProfitByConsumptionType profitByConsumptionType) {
    ProfitsByConsumptionTypeDto profitsByConsumptionTypeDto = new ProfitsByConsumptionTypeDto();
    profitsByConsumptionTypeDto.consumptionType =
        profitByConsumptionType.getConsumptionType().toString();
    profitsByConsumptionTypeDto.profits = profitByConsumptionType.getMoney().toDouble();

    return profitsByConsumptionTypeDto;
  }
}
