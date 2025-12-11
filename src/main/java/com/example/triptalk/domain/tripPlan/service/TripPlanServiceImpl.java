package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.converter.TripPlanConverter;
import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.entity.*;
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
    private final com.example.triptalk.domain.user.repository.UserRepository userRepository;

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

    @Override
    @Transactional
    public TripPlanResponse.TripPlanStatusDTO changeTripPlanStatusToTraveled(Long tripPlanId, Long userId) {
        // 1. 조회
        TripPlan tripPlan = tripPlanRepository.findWithAllById(tripPlanId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.TRIP_PLAN_NOT_FOUND));

        // 2. 권한 확인
        if (!tripPlan.getUser().getId().equals(userId)) {
            throw new ErrorHandler(ErrorStatus._FORBIDDEN);
        }

        // 3. 상태 확인
        if (tripPlan.getStatus() == TripStatus.TRAVELED) {
            throw new ErrorHandler(ErrorStatus.TRIP_PLAN_ALREADY_TRAVELED);
        }

        // 4. 상태 변경 및 저장
        tripPlan.setStatus(TripStatus.TRAVELED);
        tripPlanRepository.save(tripPlan);

        return TripPlanConverter.toTripPlanStatusDTO(tripPlan);
    }

    @Override
    @Transactional
    public TripPlanResponse.TripPlanDTO createTripPlanFromFastAPI(Long userId, com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.CreateFromFastAPIDTO request) {
        // 1. User 조회
        com.example.triptalk.domain.user.entity.User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. TripPlan 생성 (Converter 사용)
        TripPlan tripPlan = TripPlanConverter.toTripPlanEntity(request, user);
        tripPlan = tripPlanRepository.save(tripPlan);

        TripPlan finalTripPlan = tripPlan;

        // 3. Highlights 저장 (Converter 사용)
        java.util.List<TripHighlight> highlights = TripPlanConverter.toTripHighlightEntities(
                request.getHighlights(),
                finalTripPlan
        );
        tripHighlightRepository.saveAll(highlights);

        // 4. Transportations 저장 (Converter 사용)
        TripTransportation outbound = TripPlanConverter.toTripTransportationEntity(
                request.getOutboundTransportation(),
                finalTripPlan
        );
        if (outbound != null) {
            tripTransportationRepository.save(outbound);
        }

        TripTransportation returnTransport = TripPlanConverter.toTripTransportationEntity(
                request.getReturnTransportation(),
                finalTripPlan
        );
        if (returnTransport != null) {
            tripTransportationRepository.save(returnTransport);
        }

        // 5. Accommodations 저장 (Converter 사용)
        java.util.List<TripAccommodation> accommodations = TripPlanConverter.toTripAccommodationEntities(
                request.getAccommodations(),
                finalTripPlan
        );
        tripAccommodationRepository.saveAll(accommodations);

        // 6. DailySchedules 저장 (Converter 사용)
        if (request.getDailySchedules() != null) {
            for (com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.DailyScheduleDTO dailyScheduleDTO : request.getDailySchedules()) {
                DailySchedule dailySchedule = TripPlanConverter.toDailyScheduleEntity(
                        dailyScheduleDTO,
                        finalTripPlan
                );
                dailyScheduleRepository.save(dailySchedule);
            }
        }

        // 7. 저장된 데이터 조회 및 반환
        return getTripPlan(finalTripPlan.getId(), userId);
    }
}
