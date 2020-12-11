package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import ca.ulaval.glo4003.cars.services.converters.ConsumptionConverter;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessPassTypeConverter {
  private final ConsumptionConverter consumptionConverter;
  private final AccessPassPeriodConverter accessPassPeriodConverter;

  public AccessPassTypeConverter(
      ConsumptionConverter consumptionConverter,
      AccessPassPeriodConverter accessPassPeriodConverter) {
    this.consumptionConverter = consumptionConverter;
    this.accessPassPeriodConverter = accessPassPeriodConverter;
  }
  // TODO test

  public List<AccessPassType> convert(Map<String, Map<String, Double>> zonesAndFees) {
    List<AccessPassType> accessPassTypes = new ArrayList<>();

    for (Map.Entry<String, Map<String, Double>> zoneAndFee : zonesAndFees.entrySet()) {
      String consumptionType = zoneAndFee.getKey();
      ConsumptionTypeInFrench consumptionTypeInFrench =
          ConsumptionTypeInFrench.get(consumptionType);

      Map<AccessPeriod, Money> feesPerPeriod =
          getFeeByPeriodForConsumptionType(zoneAndFee.getValue());
      AccessPassType accessPassType =
          new AccessPassType(consumptionConverter.convert(consumptionTypeInFrench), feesPerPeriod);
      accessPassTypes.add(accessPassType);
    }

    return accessPassTypes;
  }

  private Map<AccessPeriod, Money> getFeeByPeriodForConsumptionType(
      Map<String, Double> zoneAndFees) {
    Map<AccessPeriod, Money> feesPerPeriod = new HashMap<>();
    for (Map.Entry<String, Double> zoneAndFee : zoneAndFees.entrySet()) {
      AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.get(zoneAndFee.getKey());
      Money fee = Money.fromDouble(zoneAndFee.getValue());

      feesPerPeriod.put(accessPassPeriodConverter.convert(accessPeriodInFrench), fee);
    }

    return feesPerPeriod;
  }
}
