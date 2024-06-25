package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Schema(
        description = "CommentDto Model Information"
)
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Schema(
            description = "Name of whom left this comment"
    )
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    @Schema(
            description = "Email of whom left this comment"
    )
    private String email;

    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message = "Comment body should have at least 10 characters")
    @Schema(
            description = "Body of this comment"
    )
    private String body;
}
