package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.domain.account.AccountId;

public class Offense {
  private String stickerCode;
  private AccountId accountId;
  private String reasonText;
  private String reasonCode;
  private int amount;

  public Offense(
      String stickerCode, AccountId accountId, String reasonText, String reasonCode, int amount) {
    this.stickerCode = stickerCode;
    this.accountId = accountId;
    this.reasonText = reasonText;
    this.reasonCode = reasonCode;
    this.amount = amount;
  }

  public String getStickerCode() {
    return stickerCode;
  }

  public AccountId getAccountId() {
    return accountId;
  }

  public String getReasonText() {
    return reasonText;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public int getAmount() {
    return amount;
  }
}
