package es.kairos.kupproject.controller;

import es.kairos.kupproject.dto.CommentDTO;
import es.kairos.kupproject.dto.EntryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Api
public interface CommentController {

  @ApiOperation(value = "Create a new comment")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Comment created succesfully")})
  public ResponseEntity<EntryDTO> createComment(
      @ApiParam(value = "comment", required = true) CommentDTO comment);

  @ApiOperation(value = "Read comment")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Comment found"),
      @ApiResponse(code = 404, message = "Comment not found")})
  public ResponseEntity getComment(@PathVariable(value = "id") Long id);

  @ApiOperation(value = "Read all comments")
  public ResponseEntity getAllComments(Pageable page);

  @ApiOperation(value = "Update comment")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Comment deleted"),
      @ApiResponse(code = 404, message = "Comment not found")})
  public ResponseEntity<CommentDTO> putComment(
      @ApiParam(value = "comment", required = true) CommentDTO comment);

  @ApiOperation(value = "Delete comment")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Comment deleted"),
      @ApiResponse(code = 404, message = "Comment not found")})
  public ResponseEntity deleteComment(@ApiParam(value = "comment", required = true) CommentDTO comment);
}
