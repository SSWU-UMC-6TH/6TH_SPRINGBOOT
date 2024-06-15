package umc.spring.service;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    @Transactional
    Member joinMember(MemberRequestDTO.JoinDto request);
}
