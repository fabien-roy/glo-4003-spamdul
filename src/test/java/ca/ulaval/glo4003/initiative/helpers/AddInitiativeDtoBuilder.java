package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;

public class AddInitiativeDtoBuilder {
  private String name = createName();
  private Double amount = createAmount();

  private AddInitiativeDtoBuilder() {}

  public static AddInitiativeDtoBuilder anAddInitiativeDto() {
    return new AddInitiativeDtoBuilder();
  }

  public AddInitiativeDto build() {
    AddInitiativeDto addInitiativeDto = new AddInitiativeDto();
    addInitiativeDto.name = name;
    addInitiativeDto.amount = amount;
    return addInitiativeDto;
  }
}
