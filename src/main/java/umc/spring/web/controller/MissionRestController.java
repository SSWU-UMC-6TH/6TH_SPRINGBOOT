package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.Mission.MissionCommandService;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.CreateResultDTO> create(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request){
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }

    @PostMapping("/challenging")
    public ApiResponse<MemberMissionResponseDTO.CreateChallengeMissionDTO> create(@RequestBody @Valid MemberMissionRequestDTO.ChallengeMission request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ApiResponse.onFailure(ErrorStatus.CHALLENGE_ALREADY_EXIST.getCode(), ErrorStatus.CHALLENGE_ALREADY_EXIST.getMessage(), null);
        }
        else {
            MemberMission memberMission = missionCommandService.createChallengeMission(request);
            return ApiResponse.onSuccess(MemberMissionConverter.toCreateResultDTO(memberMission));
        }
    }
}
