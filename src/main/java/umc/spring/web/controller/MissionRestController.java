package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.Mission.MissionCommandService;
import umc.spring.service.Mission.MissionQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.CreateResultDTO> create(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request){
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }

    @PostMapping("/challenging")
    public ApiResponse<MemberMissionResponseDTO.CreateChallengeMissionDTO> create(@RequestBody @Valid MemberMissionRequestDTO.ChallengeMission request){
            MemberMission memberMission = missionCommandService.createChallengeMission(request);
            return ApiResponse.onSuccess(MemberMissionConverter.toCreateResultDTO(memberMission));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getMissionList(@ExistStores @PathVariable(name="storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Mission> mission = missionQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.storeMissionListDTO(mission));
    }

    @GetMapping("/challenging/{memberId}")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MemberMissionResponseDTO.myMissionListDTO> getMyMissionList(@PathVariable(name="memberId") Long memberId, @RequestParam(name = "page") Integer page){
        Page<MemberMission> memberMission = missionQueryService.getMyMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberMissionConverter.myMissionListDTO(memberMission));
    }

    @PatchMapping("/complete")
    @Operation(summary = "진행 중인 미션 진행 완료로 수정 API", description = "진행 중이던 미션들을 완료 상태로 수정하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberMissionResponseDTO.UpdateToCompleteMission> updateMissionStatus(@RequestBody @Valid MemberMissionResponseDTO.UpdateToCompleteMission request){
        MemberMission memberMission = missionQueryService.updateMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateStatusDTO(memberMission));
    }
}
