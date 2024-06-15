package umc.spring.service.Store;

import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public class StoreQueryServiceImpl implements StoreQueryService{
    @Override
    public Store createStore(StoreRequestDTO.CreateStoreDTO request, Region region){
        Store newStore = StoreConverter.toStore(request, region);
        return null;
    }
}
