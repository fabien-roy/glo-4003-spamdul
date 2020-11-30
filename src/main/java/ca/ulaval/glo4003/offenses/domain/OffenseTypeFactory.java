package ca.ulaval.glo4003.offenses.domain;

import ca.ulaval.glo4003.offenses.services.assemblers.OffenseCodeAssembler;

public class OffenseTypeFactory {
  private final OffenseTypeRepository offenseTypeRepository;
  private final OffenseCodeAssembler offenseCodeAssembler;

  public OffenseTypeFactory(
      OffenseTypeRepository offenseTypeRepository, OffenseCodeAssembler offenseCodeAssembler) {
    this.offenseTypeRepository = offenseTypeRepository;
    this.offenseCodeAssembler = offenseCodeAssembler;
  }

  public OffenseType createWrongZoneOffense() {
    return offenseTypeRepository.findByCode(assembleOffenseCode("ZONE_01"));
  }

  public OffenseType createWrongDayOffense() {
    return offenseTypeRepository.findByCode(assembleOffenseCode("VIG_01"));
  }

  public OffenseType createInvalidStickerOffense() {
    return offenseTypeRepository.findByCode(assembleOffenseCode("VIG_02"));
  }

  public OffenseType createAbsentStickerOffense() {
    return offenseTypeRepository.findByCode(assembleOffenseCode("VIG_03"));
  }

  private OffenseCode assembleOffenseCode(String code) {
    return offenseCodeAssembler.assemble(code);
  }
}
