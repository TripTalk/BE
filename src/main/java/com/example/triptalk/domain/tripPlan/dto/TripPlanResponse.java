package com.example.triptalk.domain.tripPlan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.triptalk.domain.tripPlan.enums.TravelStyle;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class TripPlanResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 일정 상세 조회 응답")
    public static class TripPlanDTO {

        @Schema(description = "여행 일정 ID", example = "1")
        private Long id;

        @Schema(description = "여행 제목", example = "제주도 3박 4일 힐링 & 자연 여행")
        private String title;

        @Schema(description = "목적지", example = "제주도")
        private String destination;

        @Schema(description = "출발지", example = "김포")
        private String departure;

        @Schema(description = "시작일", example = "2025-03-15")
        private LocalDate startDate;

        @Schema(description = "종료일", example = "2025-03-18")
        private LocalDate endDate;

        @Schema(description = "동행인", example = "친구")
        private String companions;

        @Schema(description = "예산", example = "500000-1000000원")
        private String budget;

        @Schema(description = "여행 스타일 목록", example = "[\"HEALING\", \"NATURE\"]")
        private Set<TravelStyle> travelStyles;

        @Schema(description = "상태", example = "PLANNED")
        private String status;

        @Schema(description = "대표 이미지 URL", example = "https://example.com/images/jeju_trip.jpg")
        private String imageUrl;

        @Schema(description = "교통편 목록")
        private List<TransportationDTO> transportations;

        @Schema(description = "숙소 목록")
        private List<AccommodationDTO> accommodations;

        @Schema(description = "일별 일정 목록")
        private List<DailyScheduleDTO> dailySchedules;

        @Schema(description = "여행 하이라이트 목록")
        private List<HighlightDTO> highlights;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "교통편")
    public static class TransportationDTO {

        @Schema(description = "출발지", example = "김포")
        private String origin;

        @Schema(description = "도착지", example = "제주")
        private String destination;

        @Schema(description = "교통편 이름/항공사", example = "제주항공")
        private String name;

        @Schema(description = "비용", example = "89000")
        private Integer price;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "숙소")
    public static class AccommodationDTO {

        @Schema(description = "숙소명", example = "제주 오션뷰 리조트")
        private String name;

        @Schema(description = "주소", example = "서귀포시 예시주소")
        private String address;

        @Schema(description = "1박 가격", example = "120000")
        private Integer pricePerNight;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "일일 일정")
    public static class DailyScheduleDTO {

        @Schema(description = "일차", example = "1")
        private Integer day;

        @Schema(description = "날짜", example = "2025-03-15")
        private LocalDate date;

        @Schema(description = "세부 일정 목록")
        private List<ScheduleItemDTO> schedules;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "일정 항목")
    public static class ScheduleItemDTO {

        @Schema(description = "순서", example = "1")
        private Integer orderIndex;

        @Schema(description = "시간", example = "10:00")
        private LocalTime time;

        @Schema(description = "제목", example = "도착 및 렌터카 픽업")
        private String title;

        @Schema(description = "설명", example = "제주공항 도착 후 렌터카 대여")
        private String description;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "하이라이트")
    public static class HighlightDTO {

        @Schema(description = "내용", example = "성산일출봉 일출 감상")
        private String content;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 일정 목록 조회 응답 (커서 기반 슬라이스)")
    public static class TripPlanSliceDTO {

        @Schema(description = "여행 일정 ID", example = "1")
        private Long id;

        @Schema(description = "여행 제목", example = "제주도 3박 4일 힐링 & 자연 여행")
        private String title;

        @Schema(description = "교통편 목록")
        private List<TransportationDTO> transportations;

        @Schema(description = "숙소 목록")
        private List<AccommodationDTO> accommodations;

        @Schema(description = "시작일", example = "2025-03-15")
        private LocalDate startDate;

        @Schema(description = "종료일", example = "2025-03-18")
        private LocalDate endDate;

        @Schema(description = "상태", example = "PLANNED")
        private String status;

        @Schema(description = "대표 이미지 URL", example = "https://example.com/images/jeju_trip.jpg")
        private String imageUrl;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 일정 목록 조회 응답 (저장소)")
    public static class TripPlanListResultDTO {

        @Schema(description = "여행 일정 목록")
        private List<TripPlanSliceDTO> tripPlanList;

        @Schema(description = "현재 페이지의 여행 일정 개수", example = "10")
        private Integer tripPlanListSize;

        @Schema(description = "페이지 처음 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "다음 페이지가 있는지 여부", example = "true")
        private Boolean hasNext;

        @Schema(description = "다음 커서 ID (무한스크롤용)", example = "1")
        private Long nextCursorId;
    }
}
