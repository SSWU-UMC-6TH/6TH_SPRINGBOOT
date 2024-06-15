package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Region;

public class StoreRequestDTO {
    @Getter
    public static class CreateStoreDTO{
        String name;
        String address;
        Float score;
        Long region;
    }
}
