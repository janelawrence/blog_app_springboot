package com.springboot.blog.controller;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api/v1")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @Operation(
            summary = "Create a comment to a post REST API",
            description = "Create a new comment record to database. This comment is related to one post by post Id"

    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/api/v1/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all Comments by a post Id REST API",
            description = "Get all Comments posted in a post by post Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @Operation(
            summary = "Get a Comment by a comment Id and a post Id REST API",
            description = "Get a Comment by a comment Id and a post Id  from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "commentId") long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }



    @Operation(
            summary = "Update a Comment by a comment Id and a post Id REST API",
            description = "Update a Comment by a comment Id and a post Id to database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> udpateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "commentId") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateCommentById(postId, commentId, commentDto));
    }


    @Operation(
            summary = "Delete a Comment by a comment Id and a post Id REST API",
            description = "Delete a Comment by a comment Id and a post Id  from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "commentId") long commentId) {
       commentService.deleteCommentById(postId, commentId);
       return ResponseEntity.ok("Comment is deleted successfully");
    }
}
