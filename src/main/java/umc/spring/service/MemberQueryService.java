package umc.spring.service;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberQueryService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
