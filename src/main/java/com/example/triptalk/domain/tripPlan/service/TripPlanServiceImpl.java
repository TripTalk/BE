package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.converter.TripPlanConverter;
import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.entity.TripPlan;
import com.example.triptalk.domain.tripPlan.repository.*;
import com.example.triptalk.domain.user.entity.User;
import com.example.triptalk.domain.user.repository.UserRepository;
import com.example.triptalk.global.apiPayload.code.status.ErrorStatus;
import com.example.triptalk.global.apiPayload.exception.handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripPlanServiceImpl implements TripPlanService {

    private final UserRepository userRepository;
    private final TripPlanRepository tripPlanRepository;
    private final TripTransportationRepository tripTransportationRepository;
    private final TripAccommodationRepository tripAccommodationRepository;
    private final DailyScheduleRepository dailyScheduleRepository;
    private final TripHighlightRepository tripHighlightRepository;

    @Override
    public TripPlanResponse.TripPlanDTO getTripPlan(Long tripPlanId, Long userId) {

        // 1. 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 여행 계획 조회 (travelStyles, user fetch join)
        TripPlan tripPlan = tripPlanRepository.findWithAllById(tripPlanId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.TRIP_PLAN_NOT_FOUND));

        // 3. 권한 확인
        if (!tripPlan.getUser().getId().equals(userId)) {
            throw new ErrorHandler(ErrorStatus._FORBIDDEN);
        }

        // 4. 연관 데이터 조회
        var transportations = tripTransportationRepository.findByTripPlanId(tripPlanId);
        var accommodations = tripAccommodationRepository.findByTripPlanId(tripPlanId);
        var dailySchedules = dailyScheduleRepository.findByTripPlanIdWithItemsOrderByDayAsc(tripPlanId);
        var highlights = tripHighlightRepository.findByTripPlanId(tripPlanId);

        return TripPlanConverter.toTripPlanDTO(tripPlan, transportations, accommodations, dailySchedules, highlights);
    }
}

