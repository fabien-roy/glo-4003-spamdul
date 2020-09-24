package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import java.util.logging.Logger;

public class ParkingService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingStickerFactory parkingStickerFactory;
  private final AccountRepository accountRepository;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerCodeRepository parkingStickerCodeRepository;

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingStickerFactory parkingStickerFactory,
      AccountRepository accountRepository,
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerCodeRepository parkingStickerCodeRepository) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.parkingStickerFactory = parkingStickerFactory;
    this.accountRepository = accountRepository;
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerCodeRepository = parkingStickerCodeRepository;
  }

  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Account account = accountRepository.findById(parkingSticker.getAccountId());
    parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());

    parkingSticker = parkingStickerFactory.create(parkingSticker);

    account.addParkingSticker(parkingSticker);
    accountRepository.update(account);

    return parkingStickerCodeAssembler.assemble(parkingSticker.getCode());
  }

  public void validateParkingStickerCode(ParkingStickerCodeDto parkingStickerCodeDto)
      throws NotFoundParkingStickerCodeException {
    logger.info(String.format("Validate parking sticker code %s", parkingStickerCodeDto));

    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(parkingStickerCodeDto);

    ParkingStickerCode foundParkingStickerCode =
        parkingStickerCodeRepository.findById(parkingStickerCode);
    // trouver le parking sticker lié et retour de la journée (personne)
    // si la journée est dans le user, envoyé le parkingSticker day avec le user du parking sticker
    // et voir ce que ça dit
    // et valider de la journée d'entrée
    // si la journée est dans le sticker valider la journée dans le sticker avec la journée
  }
}
