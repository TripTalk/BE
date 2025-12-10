package com.example.triptalk.domain.tripPlan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class FlightResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "항공권 정보")
    public static class FlightDTO {

        @Schema(description = "항공권 ID", example = "1")
        private Long id;

        @Schema(description = "출발지 한국어명", example = "김포")
        private String originName;

        @Schema(description = "도착지 한국어명", example = "제주")
        private String destinationName;

        @Schema(description = "항공사 이름", example = "진에어 LJ313")
        private String airlineName;

        @Schema(description = "가격", example = "45000")
        private Integer price;

        @Schema(description = "출발 날짜", example = "2025-12-10")
        private LocalDate departureDate;

        @Schema(description = "도착 날짜", example = "2025-12-10")
        private LocalDate arrivalDate;

        @Schema(description = "이미지 URL", example = "https://images.unsplash.com/photo-1...")
        private String imageUrl;

        @Schema(description = "출발편 여부", example = "true")
        private Boolean isOutbound;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "항공권 목록 조회 응답 (커서 기반)")
    public static class FlightListResultDTO {

        @Schema(description = "항공권 목록")
        private List<FlightDTO> flightList;

        @Schema(description = "현재 페이지의 항공권 개수", example = "5")
        private Integer flightListSize;

        @Schema(description = "페이지 처음 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "다음 페이지가 있는지 여부", example = "true")
        private Boolean hasNext;

        @Schema(description = "다음 커서 ID (무한스크롤용)", example = "10")
        private Long nextCursorId;
    }
}

