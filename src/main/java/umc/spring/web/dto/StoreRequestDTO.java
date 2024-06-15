package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.Region;

public class StoreRequestDTO {
    @Getter
    public static class CreateStoreDTO{
        @NotBlank
        String name;
        @NotBlank
        String address;
        @NotNull
        Float score;
        @NotNull
        Long region;
    }
}
