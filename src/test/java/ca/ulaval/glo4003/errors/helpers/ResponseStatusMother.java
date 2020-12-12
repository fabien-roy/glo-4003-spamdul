package ca.ulaval.glo4003.errors.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import javax.ws.rs.core.Response.Status;

public class ResponseStatusMother {

  public static Status createStatus() {
    return randomEnum(Status.class);
  }
}
