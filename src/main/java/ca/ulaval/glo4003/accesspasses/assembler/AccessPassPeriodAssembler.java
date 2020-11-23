package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPeriodException;

public class AccessPassPeriodAssembler {
  public AccessPeriod assemble(AccessPeriodInFrench accessPeriodInFrench) {
    switch (accessPeriodInFrench) {
      case UNE_HEURE:
        return AccessPeriod.ONE_HOUR;
      case UNE_JOURNEE:
        return AccessPeriod.ONE_DAY;
      case UNE_JOURNEE_PAR_SEMAINE_POUR_SESSION:
        return AccessPeriod.ONE_DAY_BY_WEEK_FOR_SEMESTER;
      case UNE_SESSION:
        return AccessPeriod.ONE_SEMESTER;
      case DEUX_SESSIONS:
        return AccessPeriod.TWO_SEMESTERS;
      case TROIS_SESSIONS:
        return AccessPeriod.THREE_SEMESTERS;
    }

    throw new InvalidAccessPeriodException();
  }
}
