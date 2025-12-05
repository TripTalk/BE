package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;

public interface TripPlanService {

    // 여행 계획 단일 조회
    TripPlanResponse.TripPlanDTO getTripPlan(Long tripPlanId, Long userId);
}

