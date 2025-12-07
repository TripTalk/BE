package com.example.triptalk.domain.tripPlace.service;

import com.example.triptalk.domain.tripPlace.converter.TripPlaceConverter;
import com.example.triptalk.domain.tripPlace.dto.TripPlaceResponse;
import com.example.triptalk.domain.tripPlace.entity.TripPlace;
import com.example.triptalk.domain.tripPlace.enums.ThemeType;
import com.example.triptalk.domain.tripPlace.repository.TripPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripPlaceServiceImpl implements TripPlaceService {

    private final TripPlaceRepository tripPlaceRepository;

    @Override
    public TripPlaceResponse.TripPlaceListResultDTO getTripPlacesByTheme(ThemeType theme, Long cursorId) {

        // 고정 페이지 크기 5개
        final int PAGE_SIZE = 5;
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // 1. 조회
        Slice<TripPlace> slice =
                tripPlaceRepository.findByThemeWithCursor(theme, cursorId, pageable);

        // 2. DTO 변환
        var tripPlaceList = slice.getContent().stream()
                .map(TripPlaceConverter::toTripPlaceSliceDTO)
                .toList();

        // 3. 응답 생성 및 반환
        return TripPlaceConverter.toTripPlaceListResultDTO(slice, tripPlaceList);
    }
}

