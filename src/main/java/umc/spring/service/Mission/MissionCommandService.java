package umc.spring.service.Mission;

import jakarta.transaction.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    @Transactional
    Mission createMission(MissionRequestDTO.CreateMissionDTO request);

    @Transactional
    MemberMission createChallengeMission(MemberMissionRequestDTO.ChallengeMission request);
}
