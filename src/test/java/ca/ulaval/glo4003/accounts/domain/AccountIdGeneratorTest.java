package ca.ulaval.glo4003.accounts.domain;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccountIdGeneratorTest {

  private AccountIdGenerator accountIdGenerator;

  @Before
  public void setUp() {
    accountIdGenerator = new AccountIdGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentIds() {
    AccountId firstId = accountIdGenerator.generate();
    AccountId secondId = accountIdGenerator.generate();

    Truth.assertThat(firstId).isNotEqualTo(secondId);
  }
}
