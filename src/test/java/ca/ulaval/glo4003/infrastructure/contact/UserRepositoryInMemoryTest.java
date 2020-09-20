package ca.ulaval.glo4003.infrastructure.contact;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.infrastructure.user.UserRepositoryInMemory;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryInMemoryTest {
  private User user = mock(User.class);

  private UserRepositoryInMemory userRepositoryInMemory;

  @Before
  public void setUp() {
    userRepositoryInMemory = new UserRepositoryInMemory();
  }

  @Test
  public void givenUser_whenAddingUser_thenAddUserInMemory() {
    userRepositoryInMemory.save(user);
    List<User> users = new ArrayList<>(userRepositoryInMemory.getUsers().values());
    Truth.assertThat(users).contains(user);
  }

  @Test
  public void whenFindingUser_thenReturnUserFromMemory() {
    userRepositoryInMemory.save(user);
    User userFound = userRepositoryInMemory.findById(Integer.toString(user.getId()));

    List<User> users = new ArrayList<>(userRepositoryInMemory.getUsers().values());
    Truth.assertThat(users).contains(userFound);
  }

  @Test
  public void whenAddingUser_thenUserIdIsIncreased() {
    int beforeNumber = userRepositoryInMemory.getUserNumber();
    userRepositoryInMemory.save(user);
    int afterNumber = userRepositoryInMemory.getUserNumber();

    Truth.assertThat(afterNumber).isGreaterThan(beforeNumber);
  }
}
