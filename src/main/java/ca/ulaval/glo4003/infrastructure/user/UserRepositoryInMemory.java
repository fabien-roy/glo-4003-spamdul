package ca.ulaval.glo4003.infrastructure.user;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserRepository;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {
  private final Map<String, User> users = new HashMap<>();
  private int userNumber = 0;

  @Override
  public void save(User user) {
    user.setId(userNumber);
    users.put(Integer.toString(userNumber), user);

    userNumber++;
  }

  public Map<String, User> getUsers() {
    return users;
  }

  public int getUserNumber() {
    return userNumber;
  }

  @Override
  public User findById(String id) {
    return users.get(id);
  }
}
