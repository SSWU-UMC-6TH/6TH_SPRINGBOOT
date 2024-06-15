package umc.spring.service.Mission;

import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MissionRequestDTO;

public class MissionQueryServiceImpl implements MissionQueryService {
    @Override
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request, Store store){
        Mission newMission = MissionConverter.toMission(request, store);
        return null;
    }

    @Override
    public MemberMission createChallengeMission(MemberMissionRequestDTO.ChallengeMission request, Member member, Mission mission){
        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(request, member, mission);
        return null;
    }
}
