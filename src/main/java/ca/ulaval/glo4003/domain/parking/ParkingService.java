package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.Account;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerException;
import ca.ulaval.glo4003.domain.time.Days;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ParkingService {
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final AccessStatusAssembler accessStatusAssembler;
  private final ParkingStickerFactory parkingStickerFactory;
  private final AccountRepository accountRepository;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingStickerFactory parkingStickerFactory,
      AccountRepository accountRepository,
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      AccessStatusAssembler accessStatusAssembler) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.accessStatusAssembler = accessStatusAssembler;
    this.parkingStickerFactory = parkingStickerFactory;
    this.accountRepository = accountRepository;
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
  }

  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Account account = accountRepository.findById(parkingSticker.getAccountId());
    parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());

    parkingSticker = parkingStickerFactory.create(parkingSticker);

    account.addParkingSticker(parkingSticker);
    accountRepository.update(account);

    parkingStickerRepository.save(parkingSticker);

    return parkingStickerCodeAssembler.assemble(parkingSticker.getCode());
  }

  public AccessStatusDto validateParkingStickerCode(String stringCode)
      throws NotFoundParkingStickerException {
    logger.info(String.format("Validate parking sticker code %s", stringCode));

    ParkingStickerCode parkingStickerCode = parkingStickerCodeAssembler.assemble(stringCode);
    ParkingSticker parkingSticker = parkingStickerRepository.findByCode(parkingStickerCode);

    LocalDate date = LocalDate.now();
    String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();

    if (!parkingSticker.validateParkingStickerDay(Days.get(dayOfWeek)))
      return accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED.toString());

    return accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED.toString());
  }
}
