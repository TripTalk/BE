package com.example.triptalk.domain.tripPlan.scheduler;

import com.example.triptalk.domain.tripPlan.converter.AmadeusConverter;
import com.example.triptalk.domain.tripPlan.dto.AmadeusResponse;
import com.example.triptalk.domain.tripPlan.entity.Flight;
import com.example.triptalk.domain.tripPlan.repository.FlightRepository;
import com.example.triptalk.domain.tripPlan.service.AmadeusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlightScheduler {

    private final AmadeusService amadeusService;
    private final FlightRepository flightRepository;

    // 인기 노선 정의 - 다양한 국가 포함
    private static final List<RouteInfo> POPULAR_ROUTES = Arrays.asList(
            // 국내선
            new RouteInfo("GMP", "CJU", "김포", "제주"),
            new RouteInfo("ICN", "CJU", "인천", "제주"),
            new RouteInfo("GMP", "PUS", "김포", "부산"),
            new RouteInfo("ICN", "PUS", "인천", "부산"),
            new RouteInfo("GMP", "TAE", "김포", "대구"),

            // 일본
            new RouteInfo("ICN", "NRT", "인천", "도쿄(나리타)"),
            new RouteInfo("ICN", "HND", "인천", "도쿄(하네다)"),
            new RouteInfo("ICN", "KIX", "인천", "오사카"),
            new RouteInfo("ICN", "FUK", "인천", "후쿠오카"),
            new RouteInfo("ICN", "CTS", "인천", "삿포로"),
            new RouteInfo("ICN", "OKA", "인천", "오키나와"),
            new RouteInfo("PUS", "NRT", "부산", "도쿄"),
            new RouteInfo("PUS", "KIX", "부산", "오사카"),

            // 중국
            new RouteInfo("ICN", "PVG", "인천", "상하이"),
            new RouteInfo("ICN", "PEK", "인천", "베이징"),
            new RouteInfo("ICN", "CAN", "인천", "광저우"),
            new RouteInfo("ICN", "SZX", "인천", "선전"),
            new RouteInfo("ICN", "XIY", "인천", "시안"),

            // 대만/홍콩
            new RouteInfo("ICN", "TPE", "인천", "타이베이"),
            new RouteInfo("ICN", "HKG", "인천", "홍콩"),

            // 동남아시아
            new RouteInfo("ICN", "BKK", "인천", "방콕"),
            new RouteInfo("ICN", "SIN", "인천", "싱가포르"),
            new RouteInfo("ICN", "KUL", "인천", "쿠알라룸푸르"),
            new RouteInfo("ICN", "MNL", "인천", "마닐라"),
            new RouteInfo("ICN", "SGN", "인천", "호찌민"),
            new RouteInfo("ICN", "HAN", "인천", "하노이"),
            new RouteInfo("ICN", "DAD", "인천", "다낭"),
            new RouteInfo("ICN", "DPS", "인천", "발리"),
            new RouteInfo("ICN", "CEB", "인천", "세부"),
            new RouteInfo("ICN", "HKT", "인천", "푸켓"),
            new RouteInfo("PUS", "BKK", "부산", "방콕"),
            new RouteInfo("PUS", "SIN", "부산", "싱가포르"),

            // 미국
            new RouteInfo("ICN", "JFK", "인천", "뉴욕"),
            new RouteInfo("ICN", "LAX", "인천", "로스앤젤레스"),
            new RouteInfo("ICN", "SFO", "인천", "샌프란시스코"),
            new RouteInfo("ICN", "SEA", "인천", "시애틀"),
            new RouteInfo("ICN", "HNL", "인천", "호놀룰루"),
            new RouteInfo("ICN", "GUM", "인천", "괌"),

            // 캐나다
            new RouteInfo("ICN", "YVR", "인천", "밴쿠버"),
            new RouteInfo("ICN", "YYZ", "인천", "토론토"),

            // 유럽
            new RouteInfo("ICN", "LHR", "인천", "런던"),
            new RouteInfo("ICN", "CDG", "인천", "파리"),
            new RouteInfo("ICN", "FRA", "인천", "프랑크푸르트"),
            new RouteInfo("ICN", "AMS", "인천", "암스테르담"),
            new RouteInfo("ICN", "FCO", "인천", "로마"),
            new RouteInfo("ICN", "MAD", "인천", "마드리드"),
            new RouteInfo("ICN", "BCN", "인천", "바르셀로나"),
            new RouteInfo("ICN", "ZRH", "인천", "취리히"),
            new RouteInfo("ICN", "IST", "인천", "이스탄불"),

            // 중동
            new RouteInfo("ICN", "DXB", "인천", "두바이"),
            new RouteInfo("ICN", "DOH", "인천", "도하"),

            // 오세아니아
            new RouteInfo("ICN", "SYD", "인천", "시드니"),
            new RouteInfo("ICN", "MEL", "인천", "멜버른"),
            new RouteInfo("ICN", "AKL", "인천", "오클랜드"),
            new RouteInfo("ICN", "CNS", "인천", "케언스")
    );

    /**
     * 매주 월요일 새벽 3시에 항공권 데이터 업데이트
     * Cron: 초 분 시 일 월 요일
     * 0 0 3 * * MON = 매주 월요일 3시
     */
    @Scheduled(cron = "0 0 3 * * MON")
    @Transactional
    public void updateFlights() {
        log.info("=== 항공권 데이터 업데이트 시작 ===");

        try {
            // 기존 데이터 모두 삭제
            flightRepository.deleteAll();
            log.info("기존 항공권 데이터 삭제 완료");

            // 7일 후 출발 날짜
            LocalDate departureDate = LocalDate.now().plusDays(7);

            List<Flight> allFlights = new ArrayList<>();

            // 각 인기 노선별로 항공권 조회 및 저장
            for (RouteInfo route : POPULAR_ROUTES) {
                try {
                    log.info("노선 조회 중: {} → {}", route.originName, route.destinationName);

                    AmadeusResponse.FlightOffersResponse response = amadeusService.searchFlights(
                            route.originCode,
                            route.destinationCode,
                            departureDate,
                            1, // 성인 1명
                            2  // 노선당 최대 2개
                    );

                    if (response.getData() != null && !response.getData().isEmpty()) {
                        for (AmadeusResponse.FlightOffer offer : response.getData()) {
                            Flight flight = AmadeusConverter.toFlight(offer, true, 0L);
                            allFlights.add(flight);
                        }
                        log.info("노선 {}건 조회 완료", response.getData().size());
                    }

                    // API Rate Limit 방지를 위해 대기
                    Thread.sleep(1000);

                } catch (Exception e) {
                    log.error("노선 조회 실패: {} → {}, 에러: {}",
                            route.originName, route.destinationName, e.getMessage());
                }
            }

            // 일괄 저장
            flightRepository.saveAll(allFlights);
            log.info("=== 항공권 데이터 업데이트 완료: 총 {}건 ===", allFlights.size());

        } catch (Exception e) {
            log.error("항공권 데이터 업데이트 실패: {}", e.getMessage(), e);
        }
    }

    /**
     * 애플리케이션 시작 시 초기 데이터 로드
     * (최초 실행 또는 DB가 비어있을 때)
     */
    @Scheduled(initialDelay = 10000, fixedDelay = Long.MAX_VALUE) // 시작 10초 후 1회만 실행
    @Transactional
    public void initialLoadFlights() {
        long count = flightRepository.count();

        if (count == 0) {
            log.info("=== 초기 항공권 데이터 로드 시작 ===");
            updateFlights();
        } else {
            log.info("기존 항공권 데이터 존재: {}건", count);
        }
    }

    // 노선 정보 클래스
    private static class RouteInfo {
        String originCode;
        String destinationCode;
        String originName;
        String destinationName;

        RouteInfo(String originCode, String destinationCode, String originName, String destinationName) {
            this.originCode = originCode;
            this.destinationCode = destinationCode;
            this.originName = originName;
            this.destinationName = destinationName;
        }
    }
}

