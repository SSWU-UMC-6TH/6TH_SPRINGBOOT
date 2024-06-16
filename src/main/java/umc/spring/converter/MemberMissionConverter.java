package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MemberMissionResponseDTO.myMissionDTO myMissionDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.myMissionDTO.builder()
                .myName(memberMission.getMember().getName())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }
    public static MemberMissionResponseDTO.myMissionListDTO myMissionListDTO(Page<MemberMission> memberMissionList){
        List<MemberMissionResponseDTO.myMissionDTO> myMissionDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::myMissionDTO).collect(Collectors.toList());

        return MemberMissionResponseDTO.myMissionListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(myMissionDTOList.size())
                .myMissionList(myMissionDTOList)
                .build();
    }
}
