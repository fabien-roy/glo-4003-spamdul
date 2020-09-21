package ca.ulaval.glo4003.domain.account;


public class AccountNumberGenerator {
  private Integer userNextNumber = 0;

  public AccountId getUserNextNumber() {
    AccountId accountId = new AccountId(String.valueOf(userNextNumber));
    userNextNumber++;

    return accountId;
  }

  public Integer getCountNumber() {
    return this.userNextNumber;
  }
}
