package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStores;

public class ReviewRequestDTO {
    @Getter
    public static class CreateReviewDTO{
        @NotBlank
        String title;
        @NotBlank
        String body;
        @NotNull
        Float score;
        @NotNull
        Long member;
        @ExistStores
        Long store;
    }
}
