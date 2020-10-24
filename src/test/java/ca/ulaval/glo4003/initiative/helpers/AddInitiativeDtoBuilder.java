package ca.ulaval.glo4003.initiative.helpers;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;

public class AddInitiativeDtoBuilder {
  private String name = createName();
  private Double amount = createAmount();

  private AddInitiativeDtoBuilder() {}

  public static AddInitiativeDtoBuilder aAddInitiativeDto() {
    return new AddInitiativeDtoBuilder();
  }

  public AddInitiativeDtoBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public AddInitiativeDtoBuilder withAmount(Double amount) {
    this.amount = amount;
    return this;
  }

  public AddInitiativeDto build() {
    AddInitiativeDto addInitiativeDto = new AddInitiativeDto();
    addInitiativeDto.name = name;
    addInitiativeDto.amount = amount;
    return addInitiativeDto;
  }
}
