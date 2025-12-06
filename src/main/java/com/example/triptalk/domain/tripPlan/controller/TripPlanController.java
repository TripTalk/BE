package com.example.triptalk.domain.tripPlan.controller;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.service.TripPlanService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

