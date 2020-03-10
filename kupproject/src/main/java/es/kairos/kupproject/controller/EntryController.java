package es.kairos.kupproject.controller;

import es.kairos.kupproject.dto.EntryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Api
public interface EntryController {

  @ApiOperation(value = "Create a new entry")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Entry created succesfully")})
  public ResponseEntity<EntryDTO> createEntry(
      @ApiParam(value = "entry", required = true) EntryDTO entry);

  @ApiOperation(value = "Read entry")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Entry found"),
      @ApiResponse(code = 404, message = "Entry not found")})
  public ResponseEntity<EntryDTO> getEntry(@PathVariable(value = "id") Long id);

  @ApiOperation(value = "Read all entries")
  public ResponseEntity<Page<EntryDTO>> getAllEntries(Pageable page);

  @ApiOperation(value = "Update entry")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Entry deleted"),
      @ApiResponse(code = 404, message = "Entry not found")})
  public ResponseEntity<EntryDTO> putEntry(
      @ApiParam(value = "entry", required = true) EntryDTO entry);

  @ApiOperation(value = "Delete entry")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Entry deleted"),
      @ApiResponse(code = 404, message = "Entry not found")})
  public ResponseEntity deleteEntry(@ApiParam(value = "entry", required = true) EntryDTO entry);
}
