package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistSameChallenges;

public class MemberMissionRequestDTO {
    @Getter
    @ExistSameChallenges
    public static class ChallengeMission{
        @NotNull
        Integer status;
        @NotNull
        Long member;
        @NotNull
        Long mission;
    }
}
