package ca.ulaval.glo4003.parkings.services;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.communications.domain.EmailSender;
import ca.ulaval.glo4003.domain.time.Days;
import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.*;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ParkingService {
  private static final String SENDING_PARKING_STICKER_EMAIL_SUBJECT = "Votre vignette SPAMD-UL";
  private static final String SENDING_PARKING_STICKER_EMAIL_MESSAGE =
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

  public ParkingService(
      ParkingStickerAssembler parkingStickerAssembler,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingStickerFactory parkingStickerFactory,
      AccountRepository accountRepository,
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      AccessStatusAssembler accessStatusAssembler,
      EmailSender emailSender) {
    this.parkingStickerAssembler = parkingStickerAssembler;
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.accessStatusAssembler = accessStatusAssembler;
    this.parkingStickerFactory = parkingStickerFactory;
    this.accountRepository = accountRepository;
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
    this.emailSender = emailSender;
  }

  public ParkingStickerCodeDto addParkingSticker(ParkingStickerDto parkingStickerDto) {
    logger.info(String.format("Add new parking sticker %s", parkingStickerDto));

    ParkingSticker parkingSticker = parkingStickerAssembler.assemble(parkingStickerDto);

    Account account = accountRepository.findById(parkingSticker.getAccountId());
    parkingAreaRepository.findByCode(parkingSticker.getParkingAreaCode());

    parkingSticker = parkingStickerFactory.create(parkingSticker);

    if (parkingSticker.getReceptionMethod().equals(ReceptionMethods.EMAIL)) {
      emailSender.sendEmail(
          parkingSticker.getEmailAddress().toString(),
          SENDING_PARKING_STICKER_EMAIL_SUBJECT,
          String.format(
              SENDING_PARKING_STICKER_EMAIL_MESSAGE, parkingSticker.getCode().toString()));
    } else if (parkingSticker.getReceptionMethod().equals(ReceptionMethods.POSTAL)) {

    }

    account.addParkingSticker(parkingSticker);
    accountRepository.update(account);

    parkingStickerRepository.save(parkingSticker);

    // TODO : This is where we should notify observers (mail service) of new parking sticker

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
