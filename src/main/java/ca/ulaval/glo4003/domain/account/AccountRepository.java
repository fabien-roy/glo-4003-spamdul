package ca.ulaval.glo4003.domain.account;

public interface AccountRepository {
  AccountId save(Account account);

  Account findById(AccountId id);

  void update(Account account);

  // TODO add here or somewhere else a way to match a StickerID to its Account
}
