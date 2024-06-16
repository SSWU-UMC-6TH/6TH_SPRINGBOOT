package umc.spring.service.Store;

import org.springframework.data.domain.Page;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreQueryService {

    Store createStore(StoreRequestDTO.CreateStoreDTO request, Region region);

    Page<Review> getReviewList(Long StoreId, Integer page);

}
