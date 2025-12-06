package com.example.triptalk.domain.tripPlan.controller;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;
import com.example.triptalk.domain.tripPlan.service.TripPlanService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip-plan")
@RequiredArgsConstructor
@Tag(name = "여행 일정 API", description = "여행 일정 관련 API")
public class TripPlanController {

    private final TripPlanService tripPlanService;

    @GetMapping("/{tripPlanId}")
    @Operation(summary = "여행 일정 조회", description = "tripPlanId로 여행 일정을 조회합니다.")
    public ApiResponse<TripPlanResponse.TripPlanDTO> getTripPlan(
            @Parameter(description = "tripPlan ID", example = "1", required = true)
            @PathVariable Long tripPlanId
    ) {
        // 인증 구현 후 SecurityContext에서 로그인한 userId 가져오기
        TripPlanResponse.TripPlanDTO response = tripPlanService.getTripPlan(tripPlanId, 1L);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/archive")
    @Operation(summary = "저장소 조회", description = "상태(status)별로 여행 일정을 커서 기반 무한스크롤로 조회합니다.")
    public ApiResponse<TripPlanResponse.TripPlanListResultDTO> getMyTripPlans(
            @Parameter(description = "여행 상태 필터", example = "PLANNED", required = true)
            @RequestParam TripStatus status,
            @Parameter(description = "다음 커서 ID (처음 요청 시 null)", example = "null")
            @RequestParam(required = false) Long cursorId
    ) {
        // 인증 구현 후 SecurityContext에서 로그인한 userId 가져오기
        TripPlanResponse.TripPlanListResultDTO response = tripPlanService.getMyTripPlans(1L, status, cursorId);
        return ApiResponse.onSuccess(response);
    }
}

