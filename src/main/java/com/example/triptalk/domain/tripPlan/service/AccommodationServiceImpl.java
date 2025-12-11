package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.converter.AccommodationConverter;
import com.example.triptalk.domain.tripPlan.dto.AccommodationResponse;
import com.example.triptalk.domain.tripPlan.entity.Accommodation;
import com.example.triptalk.domain.tripPlan.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    private static final int PAGE_SIZE = 10; // 페이지당 숙소 개수

    @Override
    public AccommodationResponse.AccommodationListResultDTO getAccommodations(Long cursorId) {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // 커서 기반 조회
        Slice<Accommodation> slice = accommodationRepository.findAllByCursor(cursorId, pageable);

        // DTO 변환
        return AccommodationConverter.toAccommodationListResultDTO(slice);
    }
}

