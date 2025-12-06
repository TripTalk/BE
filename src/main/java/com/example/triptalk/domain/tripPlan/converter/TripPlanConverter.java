package com.example.triptalk.domain.tripPlan.converter;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.entity.*;
import com.example.triptalk.domain.tripPlan.enums.TravelStyle;
import org.springframework.data.domain.Slice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripPlanConverter {

    public static TripPlanResponse.TripPlanDTO toTripPlanDTO(
            TripPlan tripPlan,
            List<TripTransportation> transportations,
            List<TripAccommodation> accommodations,
            List<DailySchedule> dailySchedules,
            List<TripHighlight> highlights
    ) {
        return TripPlanResponse.TripPlanDTO.builder()
                .id(tripPlan.getId())
                .title(tripPlan.getTitle())
                .destination(tripPlan.getDestination())
                .departure(tripPlan.getDeparture())
                .startDate(tripPlan.getStartDate())
                .endDate(tripPlan.getEndDate())
                .companions(tripPlan.getCompanions())
                .budget(tripPlan.getBudget())
                .travelStyles(toTravelStyleDTO(tripPlan.getTravelStyles()))
                .status(tripPlan.getStatus().name())
                .imageUrl(tripPlan.getImgUrl())
                .transportations(toTransportationDTO(transportations))
                .accommodations(toAccommodationDTO(accommodations))
                .dailySchedules(toDailyScheduleDTO(dailySchedules))
                .highlights(toHighlightDTO(highlights))
                .build();
    }

    /**
     * TravelStyle Set의 방어적 복사본 생성
     * - null이면 빈 Set 반환
     * - null이 아니면 새로운 HashSet으로 복사 (원본 보호)
     */
    private static Set<TravelStyle> toTravelStyleDTO(Set<TravelStyle> styles) {
        return styles == null ? Set.of() : new HashSet<>(styles);
    }

    private static List<TripPlanResponse.TransportationDTO> toTransportationDTO(List<TripTransportation> list) {
        return list.stream()
                .map(t -> TripPlanResponse.TransportationDTO.builder()
                        .origin(t.getOrigin())
                        .destination(t.getDestination())
                        .name(t.getName())
                        .price(t.getPrice())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.AccommodationDTO> toAccommodationDTO(List<TripAccommodation> list) {
        return list.stream()
                .map(a -> TripPlanResponse.AccommodationDTO.builder()
                        .name(a.getName())
                        .address(a.getAddress())
                        .pricePerNight(a.getPricePerNight())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.DailyScheduleDTO> toDailyScheduleDTO(List<DailySchedule> dailySchedules) {
        return dailySchedules.stream()
                .map(ds -> TripPlanResponse.DailyScheduleDTO.builder()
                        .day(ds.getDay())
                        .date(ds.getDate())
                        .schedules(ds.getScheduleItems().stream()
                                .map(si -> TripPlanResponse.ScheduleItemDTO.builder()
                                        .orderIndex(si.getOrderIndex())
                                        .time(si.getTime())
                                        .title(si.getTitle())
                                        .description(si.getDescription())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.HighlightDTO> toHighlightDTO(List<TripHighlight> list) {
        return list.stream()
                .map(h -> TripPlanResponse.HighlightDTO.builder()
                        .content(h.getContent())
                        .build())
                .toList();
    }

    /**
     * TripPlan 엔티티를 TripPlanSliceDTO로 변환
     * - 저장소 조회(목록)에 필요한 필드만 포함
     */
    public static TripPlanResponse.TripPlanSliceDTO toTripPlanSliceDTO(
            TripPlan tripPlan,
            List<TripTransportation> transportations,
            List<TripAccommodation> accommodations
    ){
        return TripPlanResponse.TripPlanSliceDTO.builder()
                .id(tripPlan.getId())
                .title(tripPlan.getTitle())
                .transportations(toTransportationDTO(transportations))
                .accommodations(toAccommodationDTO(accommodations))
                .startDate(tripPlan.getStartDate())
                .endDate(tripPlan.getEndDate())
                .status(tripPlan.getStatus().toString())
                .imageUrl(tripPlan.getImgUrl())
                .build();
    }

    /**
     * Slice<TripPlan>과 관련 데이터를 TripPlanListResultDTO로 변환
     * - 커서 기반 페이징 메타데이터 포함
     */
    public static TripPlanResponse.TripPlanListResultDTO toTripPlanListResultDTO(
            Slice<TripPlan> slice,
            List<TripPlanResponse.TripPlanSliceDTO> tripPlanList
    ) {
        // 다음 커서 ID는 마지막 항목의 ID
        Long nextCursorId = tripPlanList.isEmpty() ?
                null :
                tripPlanList.getLast().getId();

        return TripPlanResponse.TripPlanListResultDTO.builder()
                .tripPlanList(tripPlanList)
                .tripPlanListSize(tripPlanList.size())
                .isFirst(slice.isFirst())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .build();
    }
}

