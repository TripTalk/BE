package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.dto.TripPlanRequest;
import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;

public interface TripPlanService {

    // 여행 계획 단일 조회
    TripPlanResponse.TripPlanDTO getTripPlan(Long tripPlanId, Long userId);

    // 저장소 조회 (status별 커서 기반 무한스크롤)
    TripPlanResponse.TripPlanListResultDTO getMyTripPlans(Long userId, TripStatus status, Long cursorId);

    // 여행 상태 변경: PLANNED -> TRAVELED
    TripPlanResponse.TripPlanStatusDTO changeTripPlanStatusToTraveled(Long tripPlanId, Long userId);

    // FastAPI에서 생성된 여행 계획 저장
    TripPlanResponse.TripPlanDTO createTripPlanFromFastAPI(Long userId, TripPlanRequest.CreateFromFastAPIDTO request);
}
