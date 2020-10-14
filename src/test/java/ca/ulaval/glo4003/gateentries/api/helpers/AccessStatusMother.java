package ca.ulaval.glo4003.gateentries.api.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.parkings.domain.AccessStatus;

public class AccessStatusMother {
  public static AccessStatus createAccessStatus() {
    return randomEnum(AccessStatus.class);
  }
}
