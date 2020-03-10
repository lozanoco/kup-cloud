package es.kairos.kupproject.service.impl;

import es.kairos.kupproject.dto.UserDTO;
import es.kairos.kupproject.exceptions.UserNotFoundException;
import es.kairos.kupproject.model.User;
import es.kairos.kupproject.service.UserService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private List<User> users;

  private AtomicLong ids = new AtomicLong();

  @Autowired
  private MapperFacade mapper;

  @PostConstruct
  private void init() {

    users = Stream.of(
        new User(1, "Juan", "juan@kairos.com"),
        new User(2, "Ana", "ana@kairos.com"),
        new User(3, "Pedro", "pedro@kairos.com"))
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO create(UserDTO user) {
    user.setId(ids.get());
    users.add(mapper.map(user, User.class));
    return user;
  }

  @Override
  public UserDTO read(long id) {
    User dbUser = users.stream().
        filter(user -> user.getId() == id)
        .findAny()
        .orElseThrow(UserNotFoundException::new);
    return mapper.map(dbUser, UserDTO.class);
  }

  @Override
  public List<UserDTO> readAll() {
    return mapper.mapAsList(users, UserDTO.class);
  }

  @Override
  public UserDTO update(UserDTO user) {
    User oldUser = users.stream().
        filter(u -> u.getId() == user.getId())
        .findAny()
        .orElseThrow(UserNotFoundException::new);

    users.remove(oldUser);
    users.add(mapper.map(user, User.class));

    return user;
  }

  @Override
  public void delete(long id) {
    User dbUser = users.stream().
        filter(u -> u.getId() == id)
        .findAny()
        .orElseThrow(UserNotFoundException::new);

    users.remove(dbUser);
  }
}
