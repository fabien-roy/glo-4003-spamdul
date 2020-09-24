package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.parking.ParkingService;
import java.util.logging.Logger;

public class OffenseService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final AccountRepository accountRepository;

  public OffenseService(AccountRepository accountRepository) {
    // TODO complete constructor
    this.accountRepository = accountRepository;
  }

  public OffenseDto addOffense(OffenseDto offenseDto) {
    logger.info(String.format("Add new offense %s", offenseDto));
    // TODO complete addOffense
    return offenseDto;
  }
}
