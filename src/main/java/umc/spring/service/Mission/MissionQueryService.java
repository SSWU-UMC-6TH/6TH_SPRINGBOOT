package umc.spring.service.Mission;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionQueryService {
    Mission createMission(MissionRequestDTO.CreateMissionDTO request, Store store);

    MemberMission createChallengeMission(MemberMissionRequestDTO.ChallengeMission request, Member member, Mission mission);

    Page<Mission> getMissionList(Long storeId, Integer page);

    Page<MemberMission> getMyMissionList(Long memberId, Integer page);
}
