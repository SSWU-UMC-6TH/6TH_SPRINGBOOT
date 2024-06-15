package umc.spring.service.Store;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    @Transactional
    Store createStore(StoreRequestDTO.CreateStoreDTO request);
}
