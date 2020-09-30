package ca.ulaval.glo4003.injection.user;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceConfigTest {

  @Mock private AccountRepository accountRepository;
  @Mock private AccountFactory accountFactory;
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private CustomDateAssembler customDateAssembler;

  private UserResourceConfig userResourceConfig;

  @Before
  public void setUp() {
    userResourceConfig = new UserResourceConfig();
  }

  @Test
  public void whenCreatingUserResource_thenReturnIt() {
    UserResource userResource =
        userResourceConfig.createUserResource(
            accountRepository, accountFactory, accountIdAssembler, customDateAssembler);

    Truth.assertThat(userResource).isNotNull();
  }
}
