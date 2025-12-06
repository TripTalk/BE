package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.converter.TripPlanConverter;
import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.entity.TripPlan;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;
import com.example.triptalk.domain.tripPlan.repository.*;
import com.example.triptalk.global.apiPayload.code.status.ErrorStatus;
import com.example.triptalk.global.apiPayload.exception.handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripPlanServiceImpl implements TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final TripTransportationRepository tripTransportationRepository;
    private final TripAccommodationRepository tripAccommodationRepository;
    private final DailyScheduleRepository dailyScheduleRepository;
    private final TripHighlightRepository tripHighlightRepository;

    @Override
    public TripPlanResponse.TripPlanDTO getTripPlan(Long tripPlanId, Long userId) {

        // 1. 여행 계획 조회 (travelStyles, user fetch join)
        TripPlan tripPlan = tripPlanRepository.findWithAllById(tripPlanId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.TRIP_PLAN_NOT_FOUND));

        // 2. 권한 확인
        if (!tripPlan.getUser().getId().equals(userId)) {
            throw new ErrorHandler(ErrorStatus._FORBIDDEN);
        }

        // 3. 연관 데이터 조회
        var transportations = tripTransportationRepository.findByTripPlanId(tripPlanId);
        var accommodations = tripAccommodationRepository.findByTripPlanId(tripPlanId);
        var dailySchedules = dailyScheduleRepository.findByTripPlanIdWithItemsOrderByDayAsc(tripPlanId);
        var highlights = tripHighlightRepository.findByTripPlanId(tripPlanId);

        return TripPlanConverter.toTripPlanDTO(tripPlan, transportations, accommodations, dailySchedules, highlights);
    }

    @Override
    public TripPlanResponse.TripPlanListResultDTO getMyTripPlans(Long userId, TripStatus status, Long cursorId) {

        // 고정 페이지 크기 5개
        final int PAGE_SIZE = 5;
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // 1. 조회
        Slice<TripPlan> slice = tripPlanRepository.findMyTripPlans(userId, status, cursorId, pageable);

        // 2. 각 TripPlan별 교통편과 숙소 정보 조회
        var tripPlanListWithDetails = slice.getContent().stream()
                .map(tripPlan -> {
                    var transportations = tripTransportationRepository.findByTripPlanId(tripPlan.getId());
                    var accommodations = tripAccommodationRepository.findByTripPlanId(tripPlan.getId());
                    return TripPlanConverter.toTripPlanSliceDTO(tripPlan, transportations, accommodations);
                })
                .toList();

        // 3. dto 변환 및 반환
        return TripPlanConverter.toTripPlanListResultDTO(slice, tripPlanListWithDetails);
    }
}

