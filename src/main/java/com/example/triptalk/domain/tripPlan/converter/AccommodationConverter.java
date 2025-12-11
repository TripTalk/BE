package com.example.triptalk.domain.tripPlan.converter;

import com.example.triptalk.domain.tripPlan.dto.AccommodationResponse;
import com.example.triptalk.domain.tripPlan.entity.Accommodation;
import org.springframework.data.domain.Slice;

import java.util.List;

public class AccommodationConverter {

    /**
     * Accommodation 엔티티를 AccommodationDTO로 변환
     */
    public static AccommodationResponse.AccommodationDTO toAccommodationDTO(Accommodation accommodation) {
        return AccommodationResponse.AccommodationDTO.builder()
                .id(accommodation.getId())
                .hotelName(accommodation.getHotelName())
                .cityName(accommodation.getCityName())
                .pricePerNight(accommodation.getPricePerNight())
                .checkInDate(accommodation.getCheckInDate())
                .checkOutDate(accommodation.getCheckOutDate())
                .imageUrl(accommodation.getImageUrl())
                .build();
    }

    /**
     * Slice<Accommodation>를 AccommodationListResultDTO로 변환
     */
    public static AccommodationResponse.AccommodationListResultDTO toAccommodationListResultDTO(Slice<Accommodation> slice) {
        List<AccommodationResponse.AccommodationDTO> accommodationList = slice.getContent().stream()
                .map(AccommodationConverter::toAccommodationDTO)
                .toList();

        // 다음 커서 ID는 마지막 항목의 ID
        Long nextCursorId = accommodationList.isEmpty() ?
                null :
                accommodationList.getLast().getId();

        return AccommodationResponse.AccommodationListResultDTO.builder()
                .accommodationList(accommodationList)
                .accommodationListSize(accommodationList.size())
                .isFirst(slice.isFirst())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .build();
    }
}

