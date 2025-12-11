package com.example.triptalk.domain.tripPlan.controller;

import com.example.triptalk.domain.tripPlan.dto.AccommodationResponse;
import com.example.triptalk.domain.tripPlan.service.AccommodationService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
@Tag(name = "숙소 API", description = "추천 숙소 조회 (매주 업데이트)")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    @Operation(
            summary = "추천 숙소 조회",
            description = """
                    **추천 숙소를 조회합니다.**
                   
                    ### 📊 응답 데이터
                    - `accommodationList`: 숙소 목록 (최대 10개씩 페이징)
                      - `hotelName`: 호텔 이름 (예: 서울 신라호텔)
                      - `cityName`: 도시 한국어명 (예: 서울)
                      - `pricePerNight`: 1박 가격 (원화, 예: 150000)
                      - `checkInDate`: 체크인 날짜
                      - `checkOutDate`: 체크아웃 날짜
                      - `imageUrl`: 호텔 이미지 URL
                    
                    ### 🔄 무한스크롤 사용법
                    1. **첫 요청**: `cursorId` 없이 호출
                    2. **다음 요청**: 응답의 `nextCursorId` 값을 `cursorId`에 전달
                    3. **마지막**: `hasNext`가 `false`일 때 종료
                    """
    )
    public ApiResponse<AccommodationResponse.AccommodationListResultDTO> getAccommodations(
            @Parameter(description = "커서 ID (다음 페이지 ID, 처음 요청 시 null)", example = "null")
            @RequestParam(required = false) Long cursorId
    ) {
        AccommodationResponse.AccommodationListResultDTO response = accommodationService.getAccommodations(cursorId);
        return ApiResponse.onSuccess(response);
    }
}

