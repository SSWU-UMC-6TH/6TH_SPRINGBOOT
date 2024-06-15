package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ExistSameChallenges;
import umc.spring.web.dto.MemberMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class SameChallengeExistValidator implements ConstraintValidator<ExistSameChallenges, MemberMissionRequestDTO.ChallengeMission> {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ExistSameChallenges constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.ChallengeMission value, ConstraintValidatorContext context) {

        boolean memberMission1 = memberMissionRepository.findByMemberId(value.getMember()).isEmpty();

        boolean memberMission2 = memberMissionRepository.findByMissionId(value.getMission()).isEmpty();

        boolean isValid = memberMission1 || memberMission2;

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.CHALLENGE_ALREADY_EXIST.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}