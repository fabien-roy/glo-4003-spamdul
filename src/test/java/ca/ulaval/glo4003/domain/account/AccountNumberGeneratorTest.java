package ca.ulaval.glo4003.domain.account;

import com.google.common.truth.Truth;
import org.junit.Test;

public class AccountNumberGeneratorTest {
  private AccountNumberGenerator accountNumberGenerator = new AccountNumberGenerator();

  @Test
  public void whenGettingNextNumber_ThenCountNumberIncreased() {
    int beforeCountNumber = this.accountNumberGenerator.getCountNumber();
    this.accountNumberGenerator.getUserNextNumber();
    int afterCountNumber = this.accountNumberGenerator.getCountNumber();

    Truth.assertThat(afterCountNumber).isGreaterThan(beforeCountNumber);
  }
}
