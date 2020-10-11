package ca.ulaval.glo4003.users;

import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.users.api.UserResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserInjectorTest {

  @Mock private AccountRepository accountRepository;
  @Mock private AccountFactory accountFactory;
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private CustomDateAssembler customDateAssembler;
  @Mock private AccessService accessService;

  private UserInjector userInjector;

  @Before
  public void setUp() {
    userInjector = new UserInjector();
  }

  @Test
  public void whenCreatingUserResource_thenReturnIt() {
    UserResource userResource =
        userInjector.createUserResource(
            accountRepository,
            accountFactory,
            accountIdAssembler,
            customDateAssembler,
            accessService);

    Truth.assertThat(userResource).isNotNull();
  }
}
