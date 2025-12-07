package com.example.triptalk.domain.tripPlace.dto;

import com.example.triptalk.domain.tripPlace.enums.ThemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TripPlaceResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행지 목록 조회 응답 (커서 기반 슬라이스)")
    public static class TripPlaceSliceDTO {

        @Schema(description = "여행지 ID", example = "1")
        private Long id;

        @Schema(description = "지역", example = "제주")
        private String region;

        @Schema(description = "설명", example = "아름다운 자연 경관이 펼쳐지는 힐링 여행지")
        private String description;

        @Schema(description = "조회수", example = "1234")
        private Integer viewCount;

        @Schema(description = "이미지 URL", example = "https://example.com/images/jeju.jpg")
        private String imgUrl;

        @Schema(description = "테마 목록", example = "[\"NATURE\", \"HEALING\"]")
        private List<ThemeType> themes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행지 목록 조회 응답")
    public static class TripPlaceListResultDTO {

        @Schema(description = "여행지 목록")
        private List<TripPlaceSliceDTO> tripPlaceList;

        @Schema(description = "현재 페이지의 여행지 개수", example = "10")
        private Integer tripPlaceListSize;

        @Schema(description = "페이지 처음 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "다음 페이지가 있는지 여부", example = "true")
        private Boolean hasNext;

        @Schema(description = "다음 커서 ID (무한스크롤용)", example = "1")
        private Long nextCursorId;
    }
}

