package com.example.demo.DTO.IN;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTOIN {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\s.,!?'-]+$",
            message = "Title contains invalid characters"
    )
    private String title;

    @NotBlank(message = "Body is required")
    @Size(min = 10, max = 5000, message = "Body must be between 10 and 5000 characters")
    private String body;
}
