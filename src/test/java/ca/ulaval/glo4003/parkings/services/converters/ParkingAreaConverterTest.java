package ca.ulaval.glo4003.parkings.services.converters;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingDataFromExcel;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingPeriod;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriodInFrench;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import com.google.common.truth.Truth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaConverterTest {
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private ParkingPeriodConverter parkingPeriodConverter;

  private ParkingAreaConverter parkingAreaConverter;

  private final Map<String, Map<String, Double>> zonesAndFees = createParkingDataFromExcel();
  private final ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private final ParkingPeriod parkingPeriod = createParkingPeriod();

  @Before
  public void setUp() {
    when(parkingAreaCodeAssembler.assemble(zonesAndFees.keySet().iterator().next()))
        .thenReturn(parkingAreaCode);
    ParkingPeriodInFrench parkingPeriodInFrench =
        ParkingPeriodInFrench.get(
            zonesAndFees.values().iterator().next().keySet().iterator().next());
    when(parkingPeriodConverter.convert(parkingPeriodInFrench)).thenReturn(parkingPeriod);
    parkingAreaConverter =
        new ParkingAreaConverter(parkingAreaCodeAssembler, parkingPeriodConverter);
  }

  @Test
  public void whenConverting_thenReturnParkingArea() {
    ParkingArea parkingArea = createParkingAre();

    List<ParkingArea> parkingAreas = parkingAreaConverter.convert(zonesAndFees);

    Truth.assertThat(parkingAreas.get(0).getCode()).isEqualTo(parkingArea.getCode());
    Truth.assertThat(parkingAreas.get(0).getFeeForPeriod(parkingPeriod))
        .isEqualTo(parkingArea.getFeeForPeriod(parkingPeriod));
  }

  private ParkingArea createParkingAre() {
    Map<ParkingPeriod, Money> feeByPeriod = new HashMap<>();
    feeByPeriod.put(
        parkingPeriod,
        Money.fromDouble(zonesAndFees.values().iterator().next().values().iterator().next()));
    return new ParkingArea(parkingAreaCode, feeByPeriod);
  }
}
