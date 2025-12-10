package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.converter.FlightConverter;
import com.example.triptalk.domain.tripPlan.dto.FlightResponse;
import com.example.triptalk.domain.tripPlan.entity.Flight;
import com.example.triptalk.domain.tripPlan.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private static final int PAGE_SIZE = 10; // 페이지당 항공권 개수

    @Override
    public FlightResponse.FlightListResultDTO getFlights(Long cursorId) {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // 커서 기반 조회
        Slice<Flight> slice = flightRepository.findAllByCursor(cursorId, pageable);

        // DTO 변환
        return FlightConverter.toFlightListResultDTO(slice);
    }
}

