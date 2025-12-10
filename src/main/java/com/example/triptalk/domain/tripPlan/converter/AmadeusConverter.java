package com.example.triptalk.domain.tripPlan.converter;

import com.example.triptalk.domain.tripPlan.dto.AmadeusResponse;
import com.example.triptalk.domain.tripPlan.entity.Flight;
import com.example.triptalk.domain.tripPlan.util.CountryImageMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AmadeusConverter {

    /**
     * Amadeus FlightOffer를 Flight 엔티티로 변환
     * @param flightOffer Amadeus API 응답
     * @param isOutbound true: 출발편, false: 귀환편
     * @param tempId 임시 ID (사용되지 않음, DB 저장 시 자동 생성)
     * @return Flight 엔티티
     */
    public static Flight toFlight(
            AmadeusResponse.FlightOffer flightOffer,
            Boolean isOutbound,
            Long tempId
    ) {
        // 첫 번째 여정 정보 추출 (출발편 또는 귀환편)
        AmadeusResponse.Itinerary itinerary = flightOffer.getItineraries()[isOutbound ? 0 : 1];
        AmadeusResponse.Segment firstSegment = itinerary.getSegments()[0];
        AmadeusResponse.Segment lastSegment = itinerary.getSegments()[itinerary.getSegments().length - 1];

        // 출발/도착 시간에서 날짜 추출
        LocalDate departureDate = parseDateTime(firstSegment.getDeparture().getAt()).toLocalDate();
        LocalDate arrivalDate = parseDateTime(lastSegment.getArrival().getAt()).toLocalDate();

        // 출발지/도착지
        String origin = firstSegment.getDeparture().getIataCode();
        String destination = lastSegment.getArrival().getIataCode();

        // 항공사 정보 (carrier code + flight number)
        String airlineName = String.format("%s %s",
                getAirlineName(firstSegment.getCarrierCode()),
                firstSegment.getNumber());

        // 가격 변환 (통화에 따라 원화로 변환)
        Integer price = convertToKRW(
                flightOffer.getPrice().getCurrency(),
                Double.parseDouble(flightOffer.getPrice().getTotal())
        );

        // 이미지 URL (도착지 기준)
        String imageUrl = CountryImageMapper.getImageUrl(destination);

        // DB 저장 시 ID는 자동 생성됨
        return Flight.builder()
                .origin(origin)
                .destination(destination)
                .airlineName(airlineName)
                .price(price)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .imageUrl(imageUrl)
                .isOutbound(isOutbound)
                .build();
    }

    /**
     * 통화를 원화(KRW)로 변환
     * @param currency 원본 통화 코드
     * @param amount 금액
     * @return 원화로 변환된 금액
     */
    private static Integer convertToKRW(String currency, Double amount) {
        // 환율 (2025년 12월 기준 대략적인 값)
        double exchangeRate = switch (currency.toUpperCase()) {
            case "KRW" -> 1.0;
            case "USD" -> 1350.0;  // 1 USD = 1,350 KRW
            case "EUR" -> 1450.0;  // 1 EUR = 1,450 KRW
            case "JPY" -> 9.0;     // 1 JPY = 9 KRW
            case "CNY" -> 190.0;   // 1 CNY = 190 KRW
            case "THB" -> 40.0;    // 1 THB = 40 KRW
            case "SGD" -> 1000.0;  // 1 SGD = 1,000 KRW
            case "HKD" -> 175.0;   // 1 HKD = 175 KRW
            case "GBP" -> 1700.0;  // 1 GBP = 1,700 KRW
            case "AUD" -> 900.0;   // 1 AUD = 900 KRW
            default -> 1350.0;     // 기본값: USD 환율
        };

        return (int) (amount * exchangeRate);
    }

    /**
     * ISO 8601 형식의 날짜/시간 문자열을 LocalDateTime으로 파싱
     * @param dateTimeStr "2025-12-10T07:30:00" 형식
     * @return LocalDateTime
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * IATA 항공사 코드를 항공사 이름으로 변환
     * @param carrierCode IATA 2자리 코드 (예: KE, OZ, 7C)
     * @return 항공사 이름
     */
    private static String getAirlineName(String carrierCode) {
        return switch (carrierCode) {
            case "KE" -> "대한항공";
            case "OZ" -> "아시아나항공";
            case "7C" -> "제주항공";
            case "LJ" -> "진에어";
            case "TW" -> "티웨이항공";
            case "RS" -> "에어서울";
            case "BX" -> "에어부산";
            case "ZE" -> "이스타항공";
            case "4V" -> "플라이강원";
            case "NH" -> "전일본공수";
            case "JL" -> "일본항공";
            case "CZ" -> "중국남방항공";
            case "MU" -> "중국동방항공";
            case "CA" -> "중국국제항공";
            case "TG" -> "타이항공";
            case "SQ" -> "싱가포르항공";
            case "VN" -> "베트남항공";
            case "PR" -> "필리핀항공";
            case "AF" -> "에어프랑스";
            case "LH" -> "루프트한자";
            case "BA" -> "영국항공";
            case "UA" -> "유나이티드항공";
            case "AA" -> "아메리칸항공";
            case "DL" -> "델타항공";
            default -> carrierCode; // 매핑되지 않은 경우 코드 그대로 반환
        };
    }
}

