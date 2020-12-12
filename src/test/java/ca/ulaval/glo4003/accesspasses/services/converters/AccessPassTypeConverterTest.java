package ca.ulaval.glo4003.accesspasses.services.converters;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassTypeMother.createAccessPassDataFromExcelSheet;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.cars.services.converters.ConsumptionConverter;
import ca.ulaval.glo4003.funds.domain.Money;
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
public class AccessPassTypeConverterTest {
  private Map<String, Map<String, Double>> zonesAndFees = createAccessPassDataFromExcelSheet();
  private ConsumptionType consumptionType = createConsumptionType();
  private AccessPeriod accessPeriod = AccessPeriod.ONE_DAY;

  @Mock private ConsumptionConverter consumptionConverter;
  @Mock private AccessPassPeriodConverter accessPassPeriodConverter;

  private AccessPassTypeConverter accessPassTypeConverter;

  @Before
  public void setUp() {
    ConsumptionTypeInFrench consumptionTypeInFrench =
        ConsumptionTypeInFrench.get(zonesAndFees.keySet().iterator().next());
    AccessPeriodInFrench accessPeriodInFrench =
        AccessPeriodInFrench.get(
            zonesAndFees.values().iterator().next().keySet().iterator().next());
    when(consumptionConverter.convert(consumptionTypeInFrench)).thenReturn(consumptionType);
    when(accessPassPeriodConverter.convert(accessPeriodInFrench)).thenReturn(accessPeriod);

    accessPassTypeConverter =
        new AccessPassTypeConverter(consumptionConverter, accessPassPeriodConverter);
  }

  @Test
  public void whenConverting_thenReturnAccessPassType() {
    AccessPassType accessPassType = createAccessPassType();

    List<AccessPassType> accessPassTypes = accessPassTypeConverter.convert(zonesAndFees);

    Truth.assertThat(accessPassTypes.get(0).getConsumptionTypes())
        .isEqualTo(accessPassType.getConsumptionTypes());
    Truth.assertThat(accessPassTypes.get(0).getFeeForPeriod(accessPeriod))
        .isEqualTo(accessPassType.getFeeForPeriod(accessPeriod));
  }

  private AccessPassType createAccessPassType() {
    Map<AccessPeriod, Money> feeByPeriod = new HashMap<>();
    feeByPeriod.put(
        accessPeriod,
        Money.fromDouble(zonesAndFees.values().iterator().next().values().iterator().next()));
    return new AccessPassType(consumptionType, feeByPeriod);
  }
}
