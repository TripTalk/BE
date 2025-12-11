package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.dto.AccommodationResponse;

public interface AccommodationService {
    /**
     * DB에 저장된 숙소 조회 (커서 기반 무한스크롤)
     * @param cursorId 커서 ID (null이면 처음부터)
     * @return 숙소 목록 응답
     */
    AccommodationResponse.AccommodationListResultDTO getAccommodations(Long cursorId);
}

