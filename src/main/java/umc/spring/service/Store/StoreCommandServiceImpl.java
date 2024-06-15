package umc.spring.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreDTO request){

        Region region = regionRepository.findById(request.getRegion())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, region);


        return storeRepository.save(newStore);
    }
}
