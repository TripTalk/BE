package com.example.triptalk.domain.tripPlan.util;

import java.util.HashMap;
import java.util.Map;

public class CountryImageMapper {

    private static final Map<String, String> COUNTRY_IMAGES = new HashMap<>();

    static {
        // 아시아
        COUNTRY_IMAGES.put("한국", "https://images.unsplash.com/photo-1538948264509-7f5ccaf1d07f");
        COUNTRY_IMAGES.put("일본", "https://images.unsplash.com/photo-1542051841857-5f90071e7989");
        COUNTRY_IMAGES.put("중국", "https://images.unsplash.com/photo-1508804185872-d7badad00f7d");
        COUNTRY_IMAGES.put("태국", "https://images.unsplash.com/photo-1552465011-b4e21bf6e79a");
        COUNTRY_IMAGES.put("베트남", "https://images.unsplash.com/photo-1559592413-7cec4d0cae2b");
        COUNTRY_IMAGES.put("싱가포르", "https://images.unsplash.com/photo-1525625293386-3f8f99389edd");
        COUNTRY_IMAGES.put("대만", "https://images.unsplash.com/photo-1526481280693-3bfa7568e0f3");
        COUNTRY_IMAGES.put("홍콩", "https://images.unsplash.com/photo-1536599018102-9f803c140fc1");
        COUNTRY_IMAGES.put("필리핀", "https://images.unsplash.com/photo-1506929562872-bb421503ef21");
        COUNTRY_IMAGES.put("인도네시아", "https://images.unsplash.com/photo-1537996194471-e657df975ab4");

        // 유럽
        COUNTRY_IMAGES.put("프랑스", "https://images.unsplash.com/photo-1502602898657-3e91760cbb34");
        COUNTRY_IMAGES.put("영국", "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad");
        COUNTRY_IMAGES.put("이탈리아", "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b");
        COUNTRY_IMAGES.put("스페인", "https://images.unsplash.com/photo-1543783207-ec64e4d95325");
        COUNTRY_IMAGES.put("독일", "https://images.unsplash.com/photo-1467269204594-9661b134dd2b");
        COUNTRY_IMAGES.put("스위스", "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99");
        COUNTRY_IMAGES.put("네덜란드", "https://images.unsplash.com/photo-1512470876302-972faa2aa9a4");
        COUNTRY_IMAGES.put("그리스", "https://images.unsplash.com/photo-1503152394-c571994fd383");
        COUNTRY_IMAGES.put("체코", "https://images.unsplash.com/photo-1541849546-216549ae216d");
        COUNTRY_IMAGES.put("오스트리아", "https://images.unsplash.com/photo-1516550893923-42d28e5677af");

        // 미주
        COUNTRY_IMAGES.put("미국", "https://images.unsplash.com/photo-1485738422979-f5c462d49f74");
        COUNTRY_IMAGES.put("캐나다", "https://images.unsplash.com/photo-1503614472-8c93d56e92ce");
        COUNTRY_IMAGES.put("멕시코", "https://images.unsplash.com/photo-1518638150340-f706e86654de");
        COUNTRY_IMAGES.put("브라질", "https://images.unsplash.com/photo-1483729558449-99ef09a8c325");
        COUNTRY_IMAGES.put("아르헨티나", "https://images.unsplash.com/photo-1589909202802-8f4aadce1849");

        // 오세아니아
        COUNTRY_IMAGES.put("호주", "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9");
        COUNTRY_IMAGES.put("뉴질랜드", "https://images.unsplash.com/photo-1507699622108-4be3abd695ad");

        // 중동
        COUNTRY_IMAGES.put("두바이", "https://images.unsplash.com/photo-1512453979798-5ea266f8880c");
        COUNTRY_IMAGES.put("터키", "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200");

        // 기본 이미지
        COUNTRY_IMAGES.put("DEFAULT", "https://images.unsplash.com/photo-1436491865332-7a61a109cc05");
    }

    /**
     * 도착지 이름에서 국가를 추출하여 이미지 URL 반환
     * @param destination 도착지 (예: "제주공항", "인천공항", "나리타공항")
     * @return 이미지 URL
     */
    public static String getImageUrl(String destination) {
        if (destination == null) {
            return COUNTRY_IMAGES.get("DEFAULT");
        }

        // 국내 공항 판별
        if (destination.contains("제주") || destination.contains("부산") ||
            destination.contains("김포") || destination.contains("인천") ||
            destination.contains("대구") || destination.contains("광주")) {
            return COUNTRY_IMAGES.get("한국");
        }

        // 국가별 키워드 매칭
        for (Map.Entry<String, String> entry : COUNTRY_IMAGES.entrySet()) {
            if (destination.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // 공항 코드 기반 매핑 (IATA 코드)
        if (destination.contains("NRT") || destination.contains("나리타") ||
            destination.contains("HND") || destination.contains("하네다")) {
            return COUNTRY_IMAGES.get("일본");
        }
        if (destination.contains("PVG") || destination.contains("PEK") ||
            destination.contains("상하이") || destination.contains("북경")) {
            return COUNTRY_IMAGES.get("중국");
        }
        if (destination.contains("BKK") || destination.contains("방콕")) {
            return COUNTRY_IMAGES.get("태국");
        }
        if (destination.contains("SIN") || destination.contains("싱가포르")) {
            return COUNTRY_IMAGES.get("싱가포르");
        }
        if (destination.contains("CDG") || destination.contains("파리")) {
            return COUNTRY_IMAGES.get("프랑스");
        }
        if (destination.contains("LHR") || destination.contains("런던")) {
            return COUNTRY_IMAGES.get("영국");
        }
        if (destination.contains("FCO") || destination.contains("로마")) {
            return COUNTRY_IMAGES.get("이탈리아");
        }
        if (destination.contains("JFK") || destination.contains("뉴욕") ||
            destination.contains("LAX") || destination.contains("로스앤젤레스")) {
            return COUNTRY_IMAGES.get("미국");
        }

        return COUNTRY_IMAGES.get("DEFAULT");
    }

    /**
     * 국가 이름으로 직접 이미지 URL 조회
     * @param countryName 국가 이름
     * @return 이미지 URL
     */
    public static String getImageUrlByCountry(String countryName) {
        return COUNTRY_IMAGES.getOrDefault(countryName, COUNTRY_IMAGES.get("DEFAULT"));
    }
}

