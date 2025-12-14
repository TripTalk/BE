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
                        .name(t.getAirlineName())
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

    /**
     * TripPlan 엔티티를 TripPlanStatusDTO로 변환
     */
    public static TripPlanResponse.TripPlanStatusDTO toTripPlanStatusDTO(TripPlan tripPlan) {
        return TripPlanResponse.TripPlanStatusDTO.builder()
                .id(tripPlan.getId())
                .status(tripPlan.getStatus().name())
                .build();
    }

    // ========== FastAPI 데이터 변환 메서드 ==========

    /**
     * FastAPI 요청 DTO를 TripPlan 엔티티로 변환
     */
    public static TripPlan toTripPlanEntity(
            com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.CreateFromFastAPIDTO request,
            com.example.triptalk.domain.user.entity.User user
    ) {
        // TravelStyles 한글 문자열을 Enum으로 변환
        Set<TravelStyle> travelStyleSet = new HashSet<>();
        if (request.getTravelStyles() != null) {
            for (String styleStr : request.getTravelStyles()) {
                TravelStyle style = mapKoreanToTravelStyle(styleStr);
                if (style != null) {
                    travelStyleSet.add(style);
                }
            }
        }

        return TripPlan.builder()
                .title(request.getTitle())
                .destination(request.getDestination())
                .departure(request.getDeparture())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .companions(request.getCompanions())
                .budget(request.getBudget())
                .travelStyles(travelStyleSet)
                .imgUrl("https://plus.unsplash.com/premium_photo-1661914240950-b0124f20a5c1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .status(com.example.triptalk.domain.tripPlan.enums.TripStatus.PLANNED)
                .user(user)
                .build();
    }

    /**
     * 한글 문자열을 TravelStyle Enum으로 매핑
     */
    private static TravelStyle mapKoreanToTravelStyle(String korean) {
        if (korean == null) {
            return null;
        }

        return switch (korean.trim()) {
            case "체험·액티비티" -> TravelStyle.ACTIVITY;
            case "자연과 함께" -> TravelStyle.NATURE;
            case "여유롭게 힐링" -> TravelStyle.HEALING;
            case "여행지 느낌 물씬" -> TravelStyle.LOCAL_VIBE;
            case "관광보다 먹방" -> TravelStyle.FOOD_FOCUS;
            case "SNS 핫플레이스" -> TravelStyle.HOTPLACE;
            case "유명 관광지는 필수" -> TravelStyle.MUST_VISIT;
            case "문화·예술·역사" -> TravelStyle.CULTURE;
            case "쇼핑은 열정적으로" -> TravelStyle.SHOPPING;
            default -> null; // 매칭되지 않는 스타일은 무시
        };
    }


    /**
     * FastAPI 하이라이트 리스트를 TripHighlight 엔티티 리스트로 변환
     */
    public static List<TripHighlight> toTripHighlightEntities(
            List<String> highlights,
            TripPlan tripPlan
    ) {
        if (highlights == null) {
            return List.of();
        }
        return highlights.stream()
                .map(content -> TripHighlight.builder()
                        .content(content)
                        .tripPlan(tripPlan)
                        .build())
                .toList();
    }

    /**
     * FastAPI 교통편 DTO를 TripTransportation 엔티티로 변환
     */
    public static TripTransportation toTripTransportationEntity(
            com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.TransportationDTO dto,
            TripPlan tripPlan
    ) {
        if (dto == null) {
            return null;
        }
        return TripTransportation.builder()
                .origin(dto.getOrigin())
                .destination(dto.getDestination())
                .airlineName(dto.getName())
                .airlineName(dto.getName())  // DB 호환을 위해 name 필드에도 동일한 값 저장
                .price(dto.getPrice())
                .tripPlan(tripPlan)
                .build();
    }

    /**
     * FastAPI 숙소 리스트를 TripAccommodation 엔티티 리스트로 변환
     */
    public static List<TripAccommodation> toTripAccommodationEntities(
            List<com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.AccommodationDTO> accommodations,
            TripPlan tripPlan
    ) {
        if (accommodations == null) {
            return List.of();
        }
        return accommodations.stream()
                .map(dto -> TripAccommodation.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .pricePerNight(dto.getPricePerNight())
                        .tripPlan(tripPlan)
                        .build())
                .toList();
    }

    /**
     * FastAPI 일별 일정 DTO를 DailySchedule 엔티티로 변환
     */
    public static DailySchedule toDailyScheduleEntity(
            com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.DailyScheduleDTO dto,
            TripPlan tripPlan
    ) {
        DailySchedule dailySchedule = DailySchedule.builder()
                .day(dto.getDay())
                .date(dto.getDate())
                .tripPlan(tripPlan)
                .build();

        // ScheduleItems 추가
        if (dto.getSchedules() != null) {
            for (com.example.triptalk.domain.tripPlan.dto.TripPlanRequest.ScheduleDTO scheduleDTO : dto.getSchedules()) {
                ScheduleItem scheduleItem = ScheduleItem.builder()
                        .orderIndex(scheduleDTO.getOrderIndex())
                        .time(java.time.LocalTime.parse(scheduleDTO.getTime()))
                        .title(scheduleDTO.getTitle())
                        .description(scheduleDTO.getDescription())
                        .dailySchedule(dailySchedule)
                        .build();
                dailySchedule.getScheduleItems().add(scheduleItem);
            }
        }

        return dailySchedule;
    }
}
