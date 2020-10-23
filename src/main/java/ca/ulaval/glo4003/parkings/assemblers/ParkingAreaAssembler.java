package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingPeriodPriceDto;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingAreaAssembler {
  public ParkingAreaDto assemble(ParkingArea parkingArea) {
    ParkingAreaDto parkingAreaDto = new ParkingAreaDto();
    parkingAreaDto.parkingArea = parkingArea.getCode().toString();

    List<ParkingPeriodPriceDto> parkingPeriodPricesDto = new ArrayList<>();
    for (Map.Entry<ParkingPeriod, Money> entry : parkingArea.getFeeForAllPeriod().entrySet()) {
      ParkingPeriodPriceDto parkingPeriodPriceDto = new ParkingPeriodPriceDto();
      parkingPeriodPriceDto.period = entry.getKey().toString();
      parkingPeriodPriceDto.price = entry.getValue().toString();
      parkingPeriodPricesDto.add(parkingPeriodPriceDto);
    }

    parkingAreaDto.parkingPeriodPrice = parkingPeriodPricesDto;

    return parkingAreaDto;
  }
}
