package es.kairos.kupproject.controller.impl;

import com.fasterxml.jackson.annotation.JsonView;
import es.kairos.kupproject.controller.UserController;
import es.kairos.kupproject.dto.UserDTO;
import es.kairos.kupproject.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
    return new ResponseEntity(userService.create(user), HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return new ResponseEntity(userService.readAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    return new ResponseEntity(userService.read(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<UserDTO> putUser(@RequestBody UserDTO user) {
    return new ResponseEntity(userService.update(user), HttpStatus.OK);
  }
}
