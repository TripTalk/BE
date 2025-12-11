package com.example.triptalk.domain.tripPlan.controller;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;
import com.example.triptalk.domain.tripPlan.service.TripPlanService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import com.example.triptalk.global.security.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip-plan")
@RequiredArgsConstructor
@Tag(name = "여행 일정 API", description = "여행 일정 관련 API")
public class TripPlanController {

    private final TripPlanService tripPlanService;
    private final AuthUtil authUtil;

    @GetMapping("/{tripPlanId}")
    @Operation(summary = "여행 일정 조회", description = "tripPlanId로 여행 일정을 조회합니다.")
    public ApiResponse<TripPlanResponse.TripPlanDTO> getTripPlan(
            @Parameter(description = "tripPlan ID", example = "1", required = true)
            @PathVariable Long tripPlanId,
            HttpServletRequest request
    ) {
        Long userId = authUtil.getUserIdFromRequest(request);
        TripPlanResponse.TripPlanDTO response = tripPlanService.getTripPlan(tripPlanId, userId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/archive")
    @Operation(summary = "저장소 조회", description = "상태(status)별로 여행 일정을 커서 기반 무한스크롤로 조회합니다.")
    public ApiResponse<TripPlanResponse.TripPlanListResultDTO> getMyTripPlans(
            @Parameter(description = "여행 상태 필터", example = "PLANNED", required = true)
            @RequestParam TripStatus status,
            @Parameter(description = "다음 커서 ID (처음 요청 시 null)", example = "null")
            @RequestParam(required = false) Long cursorId,
            HttpServletRequest request
    ) {
        Long userId = authUtil.getUserIdFromRequest(request);
        TripPlanResponse.TripPlanListResultDTO response = tripPlanService.getMyTripPlans(userId, status, cursorId);
        return ApiResponse.onSuccess(response);
    }

    @PatchMapping("/{tripPlanId}/traveled")
    @Operation(summary = "여행 상태 완료 처리", description = "여행 계획의 상태를 PLANNED에서 TRAVELED로 변경합니다.")
    public ApiResponse<TripPlanResponse.TripPlanStatusDTO> markTripPlanAsTraveled(
            @Parameter(description = "tripPlan ID", example = "1", required = true)
            @PathVariable Long tripPlanId,
            HttpServletRequest request
    ) {
        Long userId = authUtil.getUserIdFromRequest(request);
        TripPlanResponse.TripPlanStatusDTO response = tripPlanService.changeTripPlanStatusToTraveled(tripPlanId, userId);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/from-fastapi")
    @Operation(
            summary = "FastAPI 생성 여행 계획 저장",
            description = """
                    **FastAPI에서 생성된 여행 계획을 DB에 저장합니다.**
                    
                    ### 📝 저장 데이터
                    - 여행 기본 정보 (제목, 출발지, 목적지, 날짜, 예산 등)
                    - 하이라이트 목록
                    - 일별 상세 일정 (DailySchedule + ScheduleItem)
                    - 교통편 정보 (출발편, 귀환편)
                    - 숙소 정보
                    
                    ### 🔐 인증
                    - Authorization 헤더에 Bearer 토큰 필요
                    - 로그인한 사용자의 여행 계획으로 저장
                    """
    )
    public ApiResponse<TripPlanResponse.TripPlanDTO> createTripPlanFromFastAPI(
            @RequestBody com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.CreateFromFastAPIDTO request,
            HttpServletRequest httpRequest
    ) {
        Long userId = authUtil.getUserIdFromRequest(httpRequest);
        TripPlanResponse.TripPlanDTO response = tripPlanService.createTripPlanFromFastAPI(userId, request);
        return ApiResponse.onSuccess(response);
    }
}
