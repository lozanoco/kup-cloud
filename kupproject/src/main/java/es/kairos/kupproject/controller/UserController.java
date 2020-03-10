package es.kairos.kupproject.controller;

import es.kairos.kupproject.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Api
public interface UserController {

  @ApiOperation(value = "Create a new user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "User created succesfully")})
  public ResponseEntity<UserDTO> createUser(
  @ApiParam(value = "user", required = true) UserDTO user);

  @ApiOperation(value = "Read user")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User found"),
      @ApiResponse(code = 404, message = "User not found")})
  public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id", required = true) Long id);

  @ApiOperation(value = "Read all users")
  public ResponseEntity<List<UserDTO>> getAllUsers();

  @ApiOperation(value = "Update user")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User deleted"),
      @ApiResponse(code = 404, message = "User not found")})
  public ResponseEntity<UserDTO> putUser(@ApiParam(value = "user", required = true) UserDTO user);

  @ApiOperation(value = "Delete user")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User deleted"),
      @ApiResponse(code = 404, message = "User not found")})
  public ResponseEntity deleteUser(@RequestParam(value = "id", required = true) Long id);
}
