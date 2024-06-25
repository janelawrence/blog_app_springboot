package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // Get category by Id rest API

    @Operation(
            summary = "Get a Category by Id REST API",
            description = "Get a Category by Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(value = "id") long id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    //Get all categories
    @Operation(
            summary = "Get all Category REST API",
            description = "Get all Category from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> listOfCats = categoryService.getAllCategory();
        return ResponseEntity.ok(listOfCats);
    }

    @Operation(
            summary = "Create a Category REST API",
            description = "Create a Category in database"

    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 OK"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto newCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a Category by Id REST API",
            description = "Update a Category by Id to database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id")  long id,
                                                      @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @Operation(
            summary = "Delete a Category by Id REST API",
            description = "Delete a Category by Id from database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement( // for swagger UI, enable authorization head
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category has been deleted");
    }
}
