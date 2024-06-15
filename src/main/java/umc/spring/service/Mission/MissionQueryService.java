package umc.spring.service.Mission;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionQueryService {
    Mission createMission(MissionRequestDTO.CreateMissionDTO request, Store store);
}
