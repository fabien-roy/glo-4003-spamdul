package ca.ulaval.glo4003.offenses.domain;

public interface OffenseNotifier {
  void notifyOffenseWithoutParkingSticker(OffenseType offenseType);
}
