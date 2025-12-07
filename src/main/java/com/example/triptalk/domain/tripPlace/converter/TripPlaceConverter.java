package com.example.triptalk.domain.tripPlace.converter;

import com.example.triptalk.domain.tripPlace.dto.TripPlaceResponse;
import com.example.triptalk.domain.tripPlace.entity.TripPlace;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public class TripPlaceConverter {

    /**
     * TripPlace 엔티티를 TripPlaceSliceDTO로 변환
     */
    public static TripPlaceResponse.TripPlaceSliceDTO toTripPlaceSliceDTO(TripPlace tripPlace) {
        return TripPlaceResponse.TripPlaceSliceDTO.builder()
                .id(tripPlace.getId())
                .region(tripPlace.getRegion())
                .description(tripPlace.getDescription())
                .viewCount(tripPlace.getViewCount())
                .imgUrl(tripPlace.getImgUrl())
                .themes(tripPlace.getThemes() == null ? new ArrayList<>() : tripPlace.getThemes())
                .build();
    }

    /**
     * Slice<TripPlace>를 TripPlaceListResultDTO로 변환
     * - 커서 기반 페이징 메타데이터 포함
     */
    public static TripPlaceResponse.TripPlaceListResultDTO toTripPlaceListResultDTO(
            Slice<TripPlace> slice,
            List<TripPlaceResponse.TripPlaceSliceDTO> tripPlaceList
    ) {
        // 다음 커서 ID는 마지막 항목의 ID
        Long nextCursorId = tripPlaceList.isEmpty() ?
                null :
                tripPlaceList.getLast().getId();

        return TripPlaceResponse.TripPlaceListResultDTO.builder()
                .tripPlaceList(tripPlaceList)
                .tripPlaceListSize(tripPlaceList.size())
                .isFirst(slice.isFirst())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .build();
    }
}

