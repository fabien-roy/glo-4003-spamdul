package ca.ulaval.glo4003.api.offense.helpers;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseMother.*;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;

public class OffenseDtoBuilder {
  private String reasonText = createReasonText();
  private String reasonCode = createReasonCode();
  private int amount = createAmount();

  private OffenseDtoBuilder() {}

  public static OffenseDtoBuilder anOffenseDto() {
    return new OffenseDtoBuilder();
  }

  public OffenseDtoBuilder withReasonText(String reasonText) {
    this.reasonText = reasonText;
    return this;
  }

  public OffenseDtoBuilder withReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
    return this;
  }

  public OffenseDtoBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public OffenseDto build() {
    OffenseDto offenseDto = new OffenseDto();

    offenseDto.infraction = reasonText;
    offenseDto.code = reasonCode;
    offenseDto.montant = amount;

    return offenseDto;
  }
}
