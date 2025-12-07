package com.example.triptalk.domain.tripPlace.service;

import com.example.triptalk.domain.tripPlace.dto.TripPlaceResponse;
import com.example.triptalk.domain.tripPlace.enums.ThemeType;

public interface TripPlaceService {

    /**
     * 테마별 여행지 목록 조회 (커서 기반 무한스크롤)
     * @param theme 테마 필터 (null이면 전체 조회)
     * @param cursorId 다음 커서 ID (처음 요청 시 null)
     * @return 여행지 목록 응답
     */
    TripPlaceResponse.TripPlaceListResultDTO getTripPlacesByTheme(ThemeType theme, Long cursorId);
}

