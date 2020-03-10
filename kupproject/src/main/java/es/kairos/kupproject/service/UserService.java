package es.kairos.kupproject.service;

import es.kairos.kupproject.dto.UserDTO;
import java.util.List;


public interface UserService {

  UserDTO create(UserDTO user);

  List<UserDTO> readAll();

  UserDTO read(long id);

  UserDTO update(UserDTO user);

  void delete(long id);

}
