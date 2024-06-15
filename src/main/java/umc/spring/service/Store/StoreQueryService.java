package umc.spring.service.Store;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreQueryService {

    Store createStore(StoreRequestDTO.CreateStoreDTO request, Region region);
}
