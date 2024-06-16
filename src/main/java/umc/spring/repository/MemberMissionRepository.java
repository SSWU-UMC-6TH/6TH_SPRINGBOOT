package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByMemberId(Long member);

    List<MemberMission> findByMissionId(Long mission);

    Page<MemberMission> findAllByMemberIdAndStatus(Long memberId, MissionStatus missionStatus, PageRequest pageRequest);
}
