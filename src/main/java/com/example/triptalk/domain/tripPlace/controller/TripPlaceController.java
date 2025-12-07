package com.example.triptalk.domain.tripPlace.controller;

import com.example.triptalk.domain.tripPlace.dto.TripPlaceResponse;
import com.example.triptalk.domain.tripPlace.enums.ThemeType;
import com.example.triptalk.domain.tripPlace.service.TripPlaceService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip-place")
@RequiredArgsConstructor
@Tag(name = "여행지 관련 API", description = "여행지 관련 API")
public class TripPlaceController {

    private final TripPlaceService tripPlaceService;

    @GetMapping
    @Operation(summary = "여행지 목록 조회", description = "테마별로 여행지를 커서 기반 무한스크롤로 조회합니다. theme를 비우면 전체 조회됩니다.")
    public ApiResponse<TripPlaceResponse.TripPlaceListResultDTO> getTripPlaces(
            @Parameter(description = "테마 필터 (NATURE, SEA, CULTURE, HEALING, HISTORY)", example = "NATURE")
            @RequestParam(required = false) ThemeType theme,
            @Parameter(description = "다음 커서 ID (처음 요청 시 null)", example = "null")
            @RequestParam(required = false) Long cursorId
    ) {
        TripPlaceResponse.TripPlaceListResultDTO response = tripPlaceService.getTripPlacesByTheme(theme, cursorId);
        return ApiResponse.onSuccess(response);
    }
}

