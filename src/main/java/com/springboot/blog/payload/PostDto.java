package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.crypto.spec.DESedeKeySpec;
import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    @Schema(
            description = "Blog post Title"
    )
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    @Schema(
            description = "Blog post description"
    )
    private String description;

    @NotEmpty
    @Schema(
            description = "Blog post content"
    )
    private String content;

    private Set<CommentDto> comments;


    @Schema(
            description = "Blog post category"
    )
    private Long categoryId;
}
