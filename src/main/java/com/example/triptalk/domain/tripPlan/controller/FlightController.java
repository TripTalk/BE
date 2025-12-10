package com.example.triptalk.domain.tripPlan.controller;

import com.example.triptalk.domain.tripPlan.dto.FlightResponse;
import com.example.triptalk.domain.tripPlan.service.FlightService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Tag(name = "항공권 API", description = "추천 항공권 조회 (매주 업데이트)")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    @Operation(
            summary = "추천 항공권 조회",
            description = """
                    **다양한 인기 노선의 항공권을 조회합니다.**
                    
                    ### 📅 데이터 업데이트
                    - 매주 월요일 새벽 3시 자동 업데이트
                    - 항상 7일 후 출발 항공권 제공
                    - 총 60개 이상 노선 (노선당 최대 3개)
                    - Amadeus API 실시간 가격 정보 (매주 업데이트)
                    - 자동 환율 변환 (EUR, USD 등 → KRW)
                    
                    ### 🌍 제공 노선
                    - **국내선**: 김포-제주, 인천-제주, 김포-부산, 인천-부산, 김포-대구 등
                    - **일본**: 도쿄, 오사카, 후쿠오카, 삿포로, 오키나와 등
                    - **중국**: 상하이, 베이징, 광저우, 선전, 시안 등
                    - **동남아**: 방콕, 싱가포르, 다낭, 발리, 세부, 푸켓 등
                    - **미주**: 뉴욕, LA, 샌프란시스코, 시애틀, 괌, 하와이 등
                    - **유럽**: 런던, 파리, 로마, 바르셀로나, 이스탄불 등
                    - **오세아니아**: 시드니, 멜버른, 오클랜드 등
                    
                    ### 📊 응답 데이터
                    - `flightList`: 항공권 목록 (최대 10개씩 페이징)
                      - `id`: 항공권 ID
                      - `originName`: 출발지 한국어명 (예: 김포, 인천)
                      - `destinationName`: 도착지 한국어명 (예: 제주, 나리타)
                      - `airlineName`: 항공사명 + 편명 (예: 대한항공 KE1019)
                      - `price`: 가격 (원화, KRW) - 모든 통화 자동 변환
                      - `departureDate/arrivalDate`: 출발/도착 날짜
                      - `isOutbound` : 출발편 여부 (true: 출발편, false: 귀국편)

                    """
    )
    public ApiResponse<FlightResponse.FlightListResultDTO> getFlights(
            @Parameter(description = "커서 ID (다음 페이지 ID, 처음 요청 시 null)", example = "null")
            @RequestParam(required = false) Long cursorId
    ) {
        FlightResponse.FlightListResultDTO response = flightService.getFlights(cursorId);
        return ApiResponse.onSuccess(response);
    }
}

