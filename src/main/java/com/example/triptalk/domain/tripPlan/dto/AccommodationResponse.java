package com.example.triptalk.domain.tripPlan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class AccommodationResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "숙소 정보")
    public static class AccommodationDTO {

        @Schema(description = "숙소 ID", example = "1")
        private Long id;

        @Schema(description = "호텔 이름", example = "서울 롯데호텔")
        private String hotelName;

        @Schema(description = "도시 한국어명", example = "서울")
        private String cityName;

        @Schema(description = "1박 가격 (원화)", example = "150000")
        private Integer pricePerNight;

        @Schema(description = "체크인 날짜", example = "2025-12-17")
        private LocalDate checkInDate;

        @Schema(description = "체크아웃 날짜", example = "2025-12-19")
        private LocalDate checkOutDate;

        @Schema(description = "도시 대표 이미지 URL", example = "https://images.unsplash.com/...")
        private String imageUrl;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "숙소 목록 응답 (커서 기반)")
    public static class AccommodationListResultDTO {

        @Schema(description = "숙소 목록")
        private List<AccommodationDTO> accommodationList;

        @Schema(description = "현재 페이지의 숙소 개수", example = "10")
        private Integer accommodationListSize;

        @Schema(description = "페이지 처음 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "다음 페이지가 있는지 여부", example = "true")
        private Boolean hasNext;

        @Schema(description = "다음 커서 ID (무한스크롤용)", example = "150")
        private Long nextCursorId;
    }
}

