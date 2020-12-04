package ca.ulaval.glo4003.funds.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.services.dto.ParkingPeriodPriceDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingPeriodPriceAssembler {

  public List<ParkingPeriodPriceDto> assemble(Map<ParkingPeriod, Money> parkingPeriodMoney) {
    List<ParkingPeriodPriceDto> parkingPeriodPricesDto = new ArrayList<>();
    for (Map.Entry<ParkingPeriod, Money> entry : parkingPeriodMoney.entrySet()) {
      ParkingPeriodPriceDto parkingPeriodPriceDto = new ParkingPeriodPriceDto();
      parkingPeriodPriceDto.period = entry.getKey().toString();
      parkingPeriodPriceDto.price = entry.getValue().toDouble();
      parkingPeriodPricesDto.add(parkingPeriodPriceDto);
    }

    return parkingPeriodPricesDto;
  }
}
