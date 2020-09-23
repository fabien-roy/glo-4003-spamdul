package ca.ulaval.glo4003.domain.account;

public class AccountId {
  private String id;

  public AccountId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id;
  }
}
