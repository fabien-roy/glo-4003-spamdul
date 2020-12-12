package ca.ulaval.glo4003.gates.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.parkings.domain.AccessStatus;

public class AccessStatusMother {
  public static AccessStatus createAccessStatus() {
    return randomEnum(AccessStatus.class);
  }
}
