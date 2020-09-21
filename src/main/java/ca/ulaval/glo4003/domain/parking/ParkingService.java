package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import java.util.logging.Logger;

public class ParkingService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());

  public void addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));
    // TODO : Assemble parking sticker
    // TODO : Get account with repository
    // TODO : Get parking area with repository
    // TODO : Create parking sticker with code (Factory)
    // TODO : Add parking sticker to account
    // TODO : Save account in repo
  }
}
