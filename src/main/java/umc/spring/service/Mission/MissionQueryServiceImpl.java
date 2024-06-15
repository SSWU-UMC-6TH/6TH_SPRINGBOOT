package umc.spring.service.Mission;

import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;

public class MissionQueryServiceImpl implements MissionQueryService {
    @Override
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request, Store store){
        Mission newMission = MissionConverter.toMission(request, store);
        return null;
    }
}
