package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.times.domain.Days;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ParkingService {
  private static final String SENDING_PARKING_STICKER_EMAIL_SUBJECT = "Votre vignette SPAMD-UL";
  private static final String SENDING_PARKING_STICKER_EMAIL_MESSAGE =
      "Votre code de vignette SPAMD-UL est %s";
  private static final String SENDING_PARKING_STICKER_POSTAL_MESSAGE =
      "Votre code de vignette SPAMD-UL est %s";
  private final Logger logger = Logger.getLogger(ParkingService.class.getName());
  private final ParkingStickerAssembler parkingStickerAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final AccessStatusAssembler accessStatusAssembler;
  private final ParkingStickerFactory parkingStickerFactory;
  private final AccountRepository accountRepository;
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;
  private final EmailSender emailSender;
  private final PostalSender postalSender;
  private final BillService billService;

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingStickerFactory parkingStickerFactory,
      AccountRepository accountRepository,
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      AccessStatusAssembler accessStatusAssembler,
      EmailSender emailSender,
      PostalSender postalSender,
      BillService billService) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.accessStatusAssembler = accessStatusAssembler;
    this.parkingStickerFactory = parkingStickerFactory;
    this.accountRepository = accountRepository;
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
    this.emailSender = emailSender;
    this.postalSender = postalSender;
    this.billService = billService;
  }

  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Account account = accountRepository.findById(parkingSticker.getAccountId());

    parkingSticker = parkingStickerFactory.create(parkingSticker);

    if (parkingSticker.getReceptionMethod().equals(ReceptionMethods.EMAIL)) {
      emailSender.sendEmail(
          parkingSticker.getEmailAddress().toString(),
          SENDING_PARKING_STICKER_EMAIL_SUBJECT,
          String.format(
              SENDING_PARKING_STICKER_EMAIL_MESSAGE, parkingSticker.getCode().toString()));
    } else if (parkingSticker.getReceptionMethod().equals(ReceptionMethods.POSTAL)) {
      postalSender.sendPostal(
          parkingSticker.getPostalCode(),
          String.format(
              SENDING_PARKING_STICKER_POSTAL_MESSAGE, parkingSticker.getCode().toString()));
    }

    ParkingArea parkingArea = parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());
    BillId billId = billService.addBillForParkingSticker(parkingSticker, parkingArea);

    account.addParkingStickerCode(parkingSticker.getCode());
    account.addBillId(billId);
    accountRepository.update(account);

    parkingStickerRepository.save(parkingSticker);

    // TODO : Use observers instead of this if-else (here, not before)

    return parkingStickerCodeAssembler.assemble(parkingSticker.getCode());
  }

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
