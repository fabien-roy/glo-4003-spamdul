package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import java.util.logging.Logger;

public class ParkingService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final AccountRepository accountRepository;
  private final ParkingAreaRepository parkingAreaRepository;

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler,
      AccountRepository accountRepository,
      ParkingAreaRepository parkingAreaRepository) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.accountRepository = accountRepository;
    this.parkingAreaRepository = parkingAreaRepository;
  }

  public void addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));
    ParkingSticker parkingSticker = parkingStickerAssembler.create(parkingStickerDto);
    accountRepository.findById(parkingSticker.getAccountId());
    parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());
    // TODO : Create parking sticker with code (Factory)
    // TODO : Add parking sticker to account
    // TODO : Save account in repo
  }
}
