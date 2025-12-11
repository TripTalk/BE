package com.example.triptalk.domain.tripPlan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class TripPlanRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "FastAPI에서 생성된 여행 계획 생성 요청")
    public static class CreateFromFastAPIDTO {

        @Schema(description = "여행 제목", example = "제주 액티비티 탐험 5박 6일 여행")
        private String title;

        @Schema(description = "목적지", example = "제주도")
        private String destination;

        @Schema(description = "출발지", example = "서울")
        private String departure;

        @Schema(description = "여행 시작일", example = "2025-12-10")
        private LocalDate startDate;

        @Schema(description = "여행 종료일", example = "2025-12-15")
        private LocalDate endDate;

        @Schema(description = "동행인", example = "친구")
        private String companions;

        @Schema(description = "예산", example = "70만원")
        private String budget;

        @Schema(description = "여행 스타일 리스트")
        private List<String> travelStyles;

        @Schema(description = "여행 하이라이트")
        private List<String> highlights;

        @Schema(description = "일별 일정")
        private List<DailyScheduleDTO> dailySchedules;

        @Schema(description = "출발 교통편")
        private TransportationDTO outboundTransportation;

        @Schema(description = "귀환 교통편")
        private TransportationDTO returnTransportation;

        @Schema(description = "숙소 리스트")
        private List<AccommodationDTO> accommodations;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "일별 일정")
    public static class DailyScheduleDTO {

        @Schema(description = "일차", example = "1")
        private Integer day;

        @Schema(description = "날짜", example = "2025-12-10")
        private LocalDate date;

        @Schema(description = "일정 항목 리스트")
        private List<ScheduleDTO> schedules;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "일정 항목")
    public static class ScheduleDTO {

        @Schema(description = "순서", example = "1")
        @JsonProperty("order_index")
        private Integer orderIndex;

        @Schema(description = "시간", example = "07:30")
        private String time;

        @Schema(description = "제목", example = "비행기 탑승")
        private String title;

        @Schema(description = "설명", example = "김포 출발 제주행")
        private String description;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "교통편 정보")
    public static class TransportationDTO {

        @Schema(description = "출발지", example = "김포공항")
        private String origin;

        @Schema(description = "도착지", example = "제주공항")
        private String destination;

        @Schema(description = "교통편명", example = "진에어LJ313")
        private String name;

        @Schema(description = "가격", example = "55000")
        private Integer price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "숙소 정보")
    public static class AccommodationDTO {

        @Schema(description = "숙소명", example = "메종글래드 제주")
        private String name;

        @Schema(description = "주소", example = "제주시 노연로 80")
        private String address;

        @Schema(description = "1박 가격", example = "100000")
        private Integer pricePerNight;
    }
}

