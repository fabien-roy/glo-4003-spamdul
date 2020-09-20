package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.contact.dto.UserDto;

public class UserService {
  private UserRepository userRepository;
  private UserAssembler userAssembler;

  public UserService(UserRepository userRepository, UserAssembler userAssembler) {
    this.userRepository = userRepository;
    this.userAssembler = userAssembler;
  }

  public void addUser(UserDto userDto) {
    User user = this.userAssembler.create(userDto);

    this.userRepository.save(user);
  }

  public UserDto getUser(String id) {
    User user = this.userRepository.findById(id);
    return this.userAssembler.create(user);
  }
}
