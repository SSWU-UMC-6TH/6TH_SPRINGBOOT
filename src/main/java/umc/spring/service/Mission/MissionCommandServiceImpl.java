package umc.spring.service.Mission;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistSameChallenges;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request){
        Store store = storeRepository.findById(request.getStore())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Mission newMission = MissionConverter.toMission(request, store);

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    @ExistSameChallenges
    public MemberMission createChallengeMission(MemberMissionRequestDTO.ChallengeMission request){
        Member member = memberRepository.findById(request.getMember())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMission())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(request, member, mission);

        return memberMissionRepository.save(newMemberMission);
    }
}
