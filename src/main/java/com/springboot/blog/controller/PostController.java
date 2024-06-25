package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create blog post
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save a new post to database"

    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // Get all posts REST API
    @Operation(
            summary = "Get all Post REST API",
            description = "Get all Post from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir

    ){
        return new ResponseEntity<>(postService.getAllPost(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    // Get post by Id REST API
    @Operation(
            summary = "Get a Post By Id REST API",
            description = "Get a Post by Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    //Get posts by Category Id REST API
    @Operation(
            summary = "Get all Post By category Id REST API",
            description = "Get a Post by category Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategoryId(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir,
            @PathVariable(name = "categoryId") long categoryId) {

        return ResponseEntity.ok(postService.getPostByCategory(pageNo, pageSize, sortBy, sortDir, categoryId));
    }

    // Update post by Id REST API
    @Operation(
            summary = "Update a Post By Id REST API",
            description = "Update a Post by Id to database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    // Delete post by Id REST API
    @Operation(
            summary = "Delete a Post By Id REST API",
            description = "Delete a Post by Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post entity deleted successfully");
    }
}
