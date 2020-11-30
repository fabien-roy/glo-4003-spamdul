package ca.ulaval.glo4003.funds.services.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createFeePerPeriod;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.api.dto.ParkingPeriodPriceDto;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class ParkingPeriodPriceAssemblerTest {
  private ParkingPeriodPriceAssembler parkingPeriodPriceAssembler;
  private Map<ParkingPeriod, Money> parkingPeriodMoney = createFeePerPeriod();

  @Before
  public void setUp() {
    parkingPeriodPriceAssembler = new ParkingPeriodPriceAssembler();
  }

  @Test
  public void whenAssembling_thenReturnParkingPeriodPriceDto() {
    List<ParkingPeriodPriceDto> parkingPeriodPricesDto =
        parkingPeriodPriceAssembler.assemble(parkingPeriodMoney);

    assertThat(parkingPeriodPricesDto.size()).isEqualTo(parkingPeriodMoney.size());
  }
}
