package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.times.domain.Days;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ParkingStickerService extends ParkingStickerCreationObservable {
  private final Logger logger = Logger.getLogger(ParkingStickerService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final AccessStatusAssembler accessStatusAssembler;
  private final ParkingStickerFactory parkingStickerFactory;
  private final AccountService accountService;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;
  private final BillService billService;

  public ParkingStickerService(
      ParkingStickerAssembler parkingStickerAssembler,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingStickerFactory parkingStickerFactory,
      AccountService accountService,
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      AccessStatusAssembler accessStatusAssembler,
      BillService billService) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.accountService = accountService;
    this.accessStatusAssembler = accessStatusAssembler;
    this.parkingStickerFactory = parkingStickerFactory;
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
    this.billService = billService;
  }

  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);
    parkingSticker = parkingStickerFactory.create(parkingSticker);

    ParkingArea parkingArea = parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());

    BillId billId = billService.addBillForParkingSticker(parkingSticker, parkingArea);
    accountService.addParkingStickerToAccount(
        parkingSticker.getAccountId(), parkingSticker.getCode(), billId);

    parkingStickerRepository.save(parkingSticker);

    notifyParkingStickerCreated(parkingSticker);

    return parkingStickerCodeAssembler.assemble(parkingSticker.getCode());
  }

  // TODO : Remove ParkingStickerService.validateParkingStickerCode
  public AccessStatusDto validateParkingStickerCode(String stringCode) {
    logger.info(String.format("Validate parking sticker code %s", stringCode));

    ParkingStickerCode parkingStickerCode = parkingStickerCodeAssembler.assemble(stringCode);
    ParkingSticker parkingSticker = parkingStickerRepository.findByCode(parkingStickerCode);

    LocalDate date = LocalDate.now();
    String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();

    if (!parkingSticker.validateParkingStickerDay(Days.get(dayOfWeek)))
      return accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED);

    return accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED);
  }
}
