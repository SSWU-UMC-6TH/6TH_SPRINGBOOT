package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberMissionResponseDTO.CreateChallengeMissionDTO toCreateResultDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.CreateChallengeMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static MemberMission toMemberMission(MemberMissionRequestDTO.ChallengeMission request, Member member, Mission mission){

        MissionStatus missionStatus = null;

        switch (request.getStatus()){
            case 0:
                missionStatus = MissionStatus.CHALLENGING;
                break;
            case 1:
                missionStatus = MissionStatus.COMPLETE;
                break;
        }

        return MemberMission.builder()
                .status(missionStatus)
                .member(member)
                .mission(mission)
                .build();
    }
}
