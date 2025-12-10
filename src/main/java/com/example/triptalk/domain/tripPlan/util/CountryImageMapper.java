package com.example.triptalk.domain.tripPlan.util;

import java.util.HashMap;
import java.util.Map;

/**
 * IATA 공항 코드를 기반으로 국가 대표 이미지를 매핑하는 유틸리티 클래스
 *
 * Amadeus API에서 받은 IATA 코드(예: ICN, NRT, BKK)를
 * 해당 국가의 대표 이미지 URL로 변환합니다.
 */
public class CountryImageMapper {

    private static final Map<String, String> AIRPORT_IMAGES = new HashMap<>();

    static {
        // 서울/수도권 공항 → 서울 이미지 (경복궁, 한강)
        String seoulImage = "https://images.unsplash.com/photo-1532649097480-b67d52743b69?q=80&w=2232&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("ICN", seoulImage); // 인천국제공항
        AIRPORT_IMAGES.put("GMP", seoulImage); // 김포국제공항

        // 제주 공항 → 제주도 이미지 (제주 바다)
        String jejuImage = "https://images.unsplash.com/photo-1612977423916-8e4bb45b5233?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("CJU", jejuImage); // 제주국제공항

        // 부산 공항 → 부산 이미지 (해운대 해변)
        String busanImage = "https://plus.unsplash.com/premium_photo-1661963130289-aa70dd516940?q=80&w=2340&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("PUS", busanImage); // 김해국제공항 (부산)

        // 대구 공항 → 대구 이미지 (팔공산)
        String daeguImage = "https://images.unsplash.com/photo-1541446201430-cf2532e3d424?q=80&w=2340&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("TAE", daeguImage); // 대구국제공항

        // 광주 공항 → 광주 이미지 (무등산)
        String gwangjuImage = "https://images.unsplash.com/photo-1638970145126-3383dcd43279?q=80&w=2340&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("KWJ", gwangjuImage); // 광주공항

        // 여수 공항 → 여수 이미지 (여수 밤바다)
        String yeosuImage = "https://plus.unsplash.com/premium_photo-1661962711053-f73d8cb0f76f?q=80&w=2340&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("RSU", yeosuImage); // 여수공항

        // 울산 공항 → 울산 이미지 (대왕암공원)
        String ulsanImage = "https://images.unsplash.com/photo-1716902923395-1d9539c2266f?q=80&w=1674&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("USN", ulsanImage); // 울산공항

        // 청주 공항 → 청주 이미지 (속리산)
        String cheongjuImage = "https://images.unsplash.com/photo-1716902923395-1d9539c2266f?q=80&w=1674&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
        AIRPORT_IMAGES.put("CJJ", cheongjuImage); // 청주국제공항

        // 일본 공항 → 일본 이미지
        String japanImage = "https://images.unsplash.com/photo-1542051841857-5f90071e7989";
        AIRPORT_IMAGES.put("NRT", japanImage); // 도쿄 나리타국제공항
        AIRPORT_IMAGES.put("HND", japanImage); // 도쿄 하네다공항
        AIRPORT_IMAGES.put("KIX", japanImage); // 오사카 간사이국제공항
        AIRPORT_IMAGES.put("ITM", japanImage); // 오사카 이타미공항
        AIRPORT_IMAGES.put("NGO", japanImage); // 나고야 주부센트레아공항
        AIRPORT_IMAGES.put("FUK", japanImage); // 후쿠오카공항
        AIRPORT_IMAGES.put("CTS", japanImage); // 삿포로 신치토세공항
        AIRPORT_IMAGES.put("OKA", japanImage); // 오키나와 나하공항
        AIRPORT_IMAGES.put("KMJ", japanImage); // 구마모토공항
        AIRPORT_IMAGES.put("HIJ", japanImage); // 히로시마공항

        // 중국 공항 → 중국 이미지
        String chinaImage = "https://images.unsplash.com/photo-1508804185872-d7badad00f7d";
        AIRPORT_IMAGES.put("PVG", chinaImage); // 상하이 푸동국제공항
        AIRPORT_IMAGES.put("SHA", chinaImage); // 상하이 홍차오국제공항
        AIRPORT_IMAGES.put("PEK", chinaImage); // 베이징 서우두국제공항
        AIRPORT_IMAGES.put("PKX", chinaImage); // 베이징 다싱국제공항
        AIRPORT_IMAGES.put("CAN", chinaImage); // 광저우 바이윈국제공항
        AIRPORT_IMAGES.put("SZX", chinaImage); // 선전 바오안국제공항
        AIRPORT_IMAGES.put("XIY", chinaImage); // 시안 셴양국제공항
        AIRPORT_IMAGES.put("CTU", chinaImage); // 청두 솽류국제공항
        AIRPORT_IMAGES.put("WUH", chinaImage); // 우한 톈허국제공항
        AIRPORT_IMAGES.put("HGH", chinaImage); // 항저우 샤오산국제공항

        // 대만 → 대만 이미지
        String taiwanImage = "https://images.unsplash.com/photo-1526481280693-3bfa7568e0f3";
        AIRPORT_IMAGES.put("TPE", taiwanImage); // 타이베이 타오위안국제공항
        AIRPORT_IMAGES.put("TSA", taiwanImage); // 타이베이 송산공항
        AIRPORT_IMAGES.put("KHH", taiwanImage); // 가오슝국제공항

        // 홍콩/마카오 → 홍콩 이미지
        String hongkongImage = "https://images.unsplash.com/photo-1536599018102-9f803c140fc1";
        AIRPORT_IMAGES.put("HKG", hongkongImage); // 홍콩국제공항
        AIRPORT_IMAGES.put("MFM", hongkongImage); // 마카오국제공항

        // 태국 → 태국 이미지
        String thailandImage = "https://images.unsplash.com/photo-1552465011-b4e21bf6e79a";
        AIRPORT_IMAGES.put("BKK", thailandImage); // 방콕 수완나품국제공항
        AIRPORT_IMAGES.put("DMK", thailandImage); // 방콕 돈므앙국제공항
        AIRPORT_IMAGES.put("CNX", thailandImage); // 치앙마이국제공항
        AIRPORT_IMAGES.put("HKT", thailandImage); // 푸켓국제공항

        // 싱가포르 → 싱가포르 이미지
        String singaporeImage = "https://images.unsplash.com/photo-1525625293386-3f8f99389edd";
        AIRPORT_IMAGES.put("SIN", singaporeImage); // 싱가포르 창이국제공항

        // 말레이시아 → 말레이시아 이미지
        String malaysiaImage = "https://images.unsplash.com/photo-1596422846543-75c6fc197f07";
        AIRPORT_IMAGES.put("KUL", malaysiaImage); // 쿠알라룸푸르국제공항

        // 필리핀 → 필리핀 이미지
        String philippinesImage = "https://images.unsplash.com/photo-1506929562872-bb421503ef21";
        AIRPORT_IMAGES.put("MNL", philippinesImage); // 마닐라 니노이 아키노국제공항
        AIRPORT_IMAGES.put("CEB", philippinesImage); // 세부 막탄국제공항

        // 베트남 → 베트남 이미지
        String vietnamImage = "https://images.unsplash.com/photo-1559592413-7cec4d0cae2b";
        AIRPORT_IMAGES.put("SGN", vietnamImage); // 호찌민 탄손낫국제공항
        AIRPORT_IMAGES.put("HAN", vietnamImage); // 하노이 노이바이국제공항
        AIRPORT_IMAGES.put("DAD", vietnamImage); // 다낭국제공항
        AIRPORT_IMAGES.put("CXR", vietnamImage); // 나트랑 캄라인국제공항
        AIRPORT_IMAGES.put("PQC", vietnamImage); // 푸꾸옥국제공항

        // 인도네시아 → 인도네시아 이미지
        String indonesiaImage = "https://images.unsplash.com/photo-1537996194471-e657df975ab4";
        AIRPORT_IMAGES.put("DPS", indonesiaImage); // 발리 응우라라이국제공항
        AIRPORT_IMAGES.put("CGK", indonesiaImage); // 자카르타 수카르노하타국제공항

        // 미국 → 미국 이미지
        String usaImage = "https://images.unsplash.com/photo-1485738422979-f5c462d49f74";
        AIRPORT_IMAGES.put("JFK", usaImage); // 뉴욕 존 F. 케네디국제공항
        AIRPORT_IMAGES.put("EWR", usaImage); // 뉴욕 뉴어크국제공항
        AIRPORT_IMAGES.put("LGA", usaImage); // 뉴욕 라과디아공항
        AIRPORT_IMAGES.put("LAX", usaImage); // 로스앤젤레스국제공항
        AIRPORT_IMAGES.put("SFO", usaImage); // 샌프란시스코국제공항
        AIRPORT_IMAGES.put("ORD", usaImage); // 시카고 오헤어국제공항
        AIRPORT_IMAGES.put("SEA", usaImage); // 시애틀 타코마국제공항
        AIRPORT_IMAGES.put("LAS", usaImage); // 라스베이거스 매캐런국제공항
        AIRPORT_IMAGES.put("IAH", usaImage); // 휴스턴 조지부시국제공항
        AIRPORT_IMAGES.put("HNL", usaImage); // 호놀룰루국제공항
        AIRPORT_IMAGES.put("GUM", usaImage); // 괌국제공항

        // 캐나다 → 캐나다 이미지
        String canadaImage = "https://images.unsplash.com/photo-1503614472-8c93d56e92ce";
        AIRPORT_IMAGES.put("YVR", canadaImage); // 밴쿠버국제공항
        AIRPORT_IMAGES.put("YYZ", canadaImage); // 토론토 피어슨국제공항

        // 영국 → 영국 이미지
        String ukImage = "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad";
        AIRPORT_IMAGES.put("LHR", ukImage); // 런던 히드로공항
        AIRPORT_IMAGES.put("LGW", ukImage); // 런던 개트윅공항

        // 프랑스 → 프랑스 이미지
        String franceImage = "https://images.unsplash.com/photo-1502602898657-3e91760cbb34";
        AIRPORT_IMAGES.put("CDG", franceImage); // 파리 샤를드골공항
        AIRPORT_IMAGES.put("ORY", franceImage); // 파리 오를리공항

        // 독일 → 독일 이미지
        String germanyImage = "https://images.unsplash.com/photo-1467269204594-9661b134dd2b";
        AIRPORT_IMAGES.put("FRA", germanyImage); // 프랑크푸르트공항
        AIRPORT_IMAGES.put("MUC", germanyImage); // 뮌헨공항

        // 네덜란드 → 네덜란드 이미지
        String netherlandsImage = "https://images.unsplash.com/photo-1512470876302-972faa2aa9a4";
        AIRPORT_IMAGES.put("AMS", netherlandsImage); // 암스테르담 스키폴공항

        // 이탈리아 → 이탈리아 이미지
        String italyImage = "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b";
        AIRPORT_IMAGES.put("FCO", italyImage); // 로마 피우미치노공항

        // 스페인 → 스페인 이미지
        String spainImage = "https://images.unsplash.com/photo-1543783207-ec64e4d95325";
        AIRPORT_IMAGES.put("MAD", spainImage); // 마드리드 바라하스공항
        AIRPORT_IMAGES.put("BCN", spainImage); // 바르셀로나 엘프라트공항

        // 스위스 → 스위스 이미지
        String switzerlandImage = "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99";
        AIRPORT_IMAGES.put("ZRH", switzerlandImage); // 취리히공항

        // 오스트리아 → 오스트리아 이미지
        String austriaImage = "https://images.unsplash.com/photo-1516550893923-42d28e5677af";
        AIRPORT_IMAGES.put("VIE", austriaImage); // 빈국제공항

        // 체코 → 체코 이미지
        String czechImage = "https://images.unsplash.com/photo-1541849546-216549ae216d";
        AIRPORT_IMAGES.put("PRG", czechImage); // 프라하 바츨라프하벨공항

        // 터키 → 터키 이미지
        String turkeyImage = "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200";
        AIRPORT_IMAGES.put("IST", turkeyImage); // 이스탄불공항

        // 그리스 → 그리스 이미지
        String greeceImage = "https://images.unsplash.com/photo-1503152394-c571994fd383";
        AIRPORT_IMAGES.put("ATH", greeceImage); // 아테네국제공항

        // UAE → UAE 이미지
        String uaeImage = "https://images.unsplash.com/photo-1512453979798-5ea266f8880c";
        AIRPORT_IMAGES.put("DXB", uaeImage); // 두바이국제공항
        AIRPORT_IMAGES.put("AUH", uaeImage); // 아부다비국제공항

        // 카타르 → 카타르 이미지
        String qatarImage = "https://images.unsplash.com/photo-1570544820879-53f4e9872689";
        AIRPORT_IMAGES.put("DOH", qatarImage); // 도하 하마드국제공항

        // 호주 → 호주 이미지
        String australiaImage = "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9";
        AIRPORT_IMAGES.put("SYD", australiaImage); // 시드니 킹스포드스미스공항
        AIRPORT_IMAGES.put("MEL", australiaImage); // 멜버른공항
        AIRPORT_IMAGES.put("BNE", australiaImage); // 브리즈번공항
        AIRPORT_IMAGES.put("CNS", australiaImage); // 케언스공항

        // 뉴질랜드 → 뉴질랜드 이미지
        String nzImage = "https://images.unsplash.com/photo-1507699622108-4be3abd695ad";
        AIRPORT_IMAGES.put("AKL", nzImage); // 오클랜드공항

        // 브라질 → 브라질 이미지
        String brazilImage = "https://images.unsplash.com/photo-1483729558449-99ef09a8c325";
        AIRPORT_IMAGES.put("GRU", brazilImage); // 상파울루 과룰류스국제공항

        // 아르헨티나 → 아르헨티나 이미지
        String argentinaImage = "https://images.unsplash.com/photo-1589909202802-8f4aadce1849";
        AIRPORT_IMAGES.put("EZE", argentinaImage); // 부에노스아이레스 에세이사국제공항
    }

    /**
     * IATA 공항 코드를 기반으로 국가 대표 이미지 URL 반환
     * @param iataCode IATA 공항 코드 (예: ICN, NRT, BKK)
     * @return 국가 대표 이미지 URL
     */
    public static String getImageUrl(String iataCode) {
        if (iataCode == null || iataCode.trim().isEmpty()) {
            return getDefaultImage();
        }

        String imageUrl = AIRPORT_IMAGES.get(iataCode.toUpperCase());

        // 매핑되지 않은 공항은 기본 여행 이미지 반환
        return imageUrl != null ? imageUrl : getDefaultImage();
    }

    /**
     * 기본 여행 이미지 URL 반환
     * @return 기본 이미지 URL
     */
    private static String getDefaultImage() {
        return "https://images.unsplash.com/photo-1436491865332-7a61a109cc05"; // 비행기 이미지
    }
}

