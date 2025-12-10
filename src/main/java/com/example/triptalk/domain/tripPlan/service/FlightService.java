package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.dto.FlightResponse;

public interface FlightService {
    /**
     * DB에 저장된 항공권 조회 (커서 기반 무한스크롤)
     * @param cursorId 커서 ID (null이면 처음부터)
     * @return 항공권 목록 응답
     */
    FlightResponse.FlightListResultDTO getFlights(Long cursorId);
}

