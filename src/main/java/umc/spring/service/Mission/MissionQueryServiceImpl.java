package umc.spring.service.Mission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

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

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return missionPage;
    }

    @Override
    public Page<MemberMission> getMyMissionList(Long memberId, Integer page){

        Page<MemberMission> myMissionPage = memberMissionRepository.findAllByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
        return myMissionPage;
    }

    @Override
    @Transactional
    public MemberMission updateMissionStatus(MemberMissionResponseDTO.UpdateToCompleteMission request){
        MemberMission memberMission = memberMissionRepository.findById(request.getMemberMissionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        memberMission.updateStatus(request.getMemberMissionId());
        return memberMission;
    }
}
