package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.offenses.api.dto.OffenseDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerException;
import ca.ulaval.glo4003.infrastructure.offense.OffenseRepositoryInMemory;
import java.util.ArrayList;
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
    this.offenseAssembler = new OffenseAssembler();
    this.offenseRepository = new OffenseRepositoryInMemory();
  }

  public List<OffenseDto> getAllOffenses() {
    // TODO This is all hard-coded for now, eventually there should be file thingies going on
    // instead
    ArrayList<Offense> offenses = new ArrayList<>();
    // Those will be reused far more often
    offenses.add(createWrongZoneOffense());
    offenses.add(createWrongDayOffense());
    offenses.add(createInvalidStickerOffense());
    // Those have to be treated "manually" by the officer
    offenses.add(new Offense("temps dépassé", OffenseCodes.TEMPS_01, 47));
    offenses.add(
        new Offense("stationnement réservé pour voiture électrique", OffenseCodes.ZONE_02, 55));
    offenses.add(new Offense("pas de vignette", OffenseCodes.VIG_03, 55));
    offenses.add(
        new Offense("la vignette et la plaque ne sont pas associées", OffenseCodes.VIG_04, 42));
    offenses.add(
        new Offense(
            "stationnement réservé pour voiture électrique branchée", OffenseCodes.ZONE_03, 55));
    return offenseAssembler.assembleOffenseDtos(offenses);
  }

  public OffenseDto validateOffense(OffenseValidationDto offenseValidationDto) {
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
    return new Offense("mauvaise zone", OffenseCodes.ZONE_01, 55);
  }

  private Offense createWrongDayOffense() {
    return new Offense("vignette pas admissible pour la journée", OffenseCodes.VIG_01, 22);
  }

  private Offense createInvalidStickerOffense() {
    return new Offense("vignette invalide", OffenseCodes.VIG_02, 45);
  }
}
