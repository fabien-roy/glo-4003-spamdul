package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.interfaces.Randomizer.randomEnum;

import ca.ulaval.glo4003.domain.parking.AccessStatus;

public class AccessStatusMother {
  public static AccessStatus createAccessStatus() {
    return randomEnum(AccessStatus.class);
  }
}
