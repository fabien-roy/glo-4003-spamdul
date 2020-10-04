package ca.ulaval.glo4003.offenses.services;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import java.util.List;

public class OffenseTypeService {
  private final ParkingStickerRepository parkingStickerRepository;
  private final OffenseValidationAssembler offenseValidationAssembler;
  private final OffenseTypeAssembler offenseTypeAssembler;
  private final OffenseTypeRepository offenseTypeRepository;

  public OffenseTypeService(
      ParkingStickerRepository parkingStickerRepository,
      OffenseValidationAssembler offenseValidationAssembler,
      OffenseTypeAssembler offenseTypeAssembler,
      OffenseTypeRepository offenseTypeRepository) {
    this.parkingStickerRepository = parkingStickerRepository;
    this.offenseValidationAssembler = offenseValidationAssembler;
    this.offenseTypeAssembler = offenseTypeAssembler;
    this.offenseTypeRepository = offenseTypeRepository;
  }

  public List<OffenseTypeDto> getAllOffenseTypes() {
    return offenseTypeAssembler.assembleMany(offenseTypeRepository.getAll());
  }

  public OffenseTypeDto validateOffense(OffenseValidationDto offenseValidationDto) {
    ParkingSticker parkingSticker;

    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    try {
      parkingSticker =
          parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode());
    } catch (NotFoundParkingStickerException e) {
      return offenseTypeAssembler.assemble(createInvalidStickerOffense());
    }

    if (!parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode())) {
      return offenseTypeAssembler.assemble(createWrongZoneOffense());
    }

    return offenseTypeAssembler.assemble(createNoOffense());
  }

  private OffenseType createNoOffense() {
    return new OffenseType("Aucune infraction signalée", OffenseCodes.NONE, 0);
  }

  private OffenseType createWrongZoneOffense() {
    return offenseTypeRepository.findByCode(OffenseCodes.ZONE_01);
  }

  private OffenseType createWrongDayOffense() {
    return offenseTypeRepository.findByCode(OffenseCodes.VIG_01);
  }

  private OffenseType createInvalidStickerOffense() {
    return offenseTypeRepository.findByCode(OffenseCodes.VIG_02);
  }
}
