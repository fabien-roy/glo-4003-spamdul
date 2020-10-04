package ca.ulaval.glo4003.offenses.services;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseRepository;
import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import java.util.List;

public class OffenseService {
  private final ParkingStickerRepository parkingStickerRepository;
  private final OffenseValidationAssembler offenseValidationAssembler;
  private final OffenseAssembler offenseAssembler;
  private final OffenseRepository offenseRepository;

  public OffenseService(
      ParkingStickerRepository parkingStickerRepository,
      OffenseValidationAssembler offenseValidationAssembler,
      OffenseAssembler offenseAssembler,
      OffenseRepository offenseRepository) {
    this.parkingStickerRepository = parkingStickerRepository;
    this.offenseValidationAssembler = offenseValidationAssembler;
    this.offenseAssembler = offenseAssembler;
    this.offenseRepository = offenseRepository;
  }

  public List<OffenseTypeDto> getAllOffenses() {
    return offenseAssembler.assembleMany(offenseRepository.getAll());
  }

  public OffenseTypeDto validateOffense(OffenseValidationDto offenseValidationDto) {
    ParkingSticker parkingSticker;

    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    try {
      parkingSticker =
          parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode());
    } catch (NotFoundParkingStickerException e) {
      return offenseAssembler.assemble(createInvalidStickerOffense());
    }

    if (!parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode())) {
      return offenseAssembler.assemble(createWrongZoneOffense());
    }

    return offenseAssembler.assemble(createNoOffense());
  }

  private Offense createNoOffense() {
    return new Offense("Aucune infraction signalée", OffenseCodes.NONE, 0);
  }

  private Offense createWrongZoneOffense() {
    return offenseRepository.findByCode(OffenseCodes.ZONE_01);
  }

  private Offense createWrongDayOffense() {
    return offenseRepository.findByCode(OffenseCodes.VIG_01);
  }

  private Offense createInvalidStickerOffense() {
    return offenseRepository.findByCode(OffenseCodes.VIG_02);
  }
}
