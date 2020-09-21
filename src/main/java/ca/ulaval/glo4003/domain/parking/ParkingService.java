package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import java.util.logging.Logger;

public class ParkingService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final AccountRepository accountRepository;

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler, AccountRepository accountRepository) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.accountRepository = accountRepository;
  }

  public void addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));
    ParkingSticker parkingSticker = parkingStickerAssembler.create(parkingStickerDto);
    accountRepository.findById(parkingSticker.getAccountId());
    // TODO : Get parking area with repository
    // TODO : Create parking sticker with code (Factory)
    // TODO : Add parking sticker to account
    // TODO : Save account in repo
  }
}
