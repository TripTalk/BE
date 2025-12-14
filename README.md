# 🌏 TripTalk - AI 기반 여행 계획 플랫폼

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/385c2e64-02f5-45a6-8868-732c572acc85" />

## 👥 팀 정보
<div align="center">
  <table>
  <tr>
    <!-- 사진 + 이름 행 -->
    <td align="center">
      <a href="https://github.com/Yujin">
        <img
          width="170"
          src="https://avatars.githubusercontent.com/Yujin1219"
          alt="유진"
        />
        <br />
        유진
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/jooyoi">
        <img
          width="170"
          src="https://avatars.githubusercontent.com/jooyoi"
          alt="홍주영"
        />
        <br />
        홍주영
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/ys1217-gg">
        <img
          width="170"
          src="https://avatars.githubusercontent.com/ys1217-gg"
          alt="황요성"
        />
        <br />
        황요성
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/davidcho0701">
        <img
          width="170"
          src="https://avatars.githubusercontent.com/davidcho0701"
          alt="조성빈"
        />
        <br />
        조성빈
      </a>
    </td>
  </tr>

  <!-- 역할 행 -->
  <tr>
    <td align="center"><b>Lead, BE</b></td>
    <td align="center"><b>AI</b></td>
    <td align="center"><b>FE</b></td>
    <td align="center"><b>FE</b></td>
  </tr>
</table>

</div>

---

## 🎯 프로젝트 개요
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/b4468b0b-ea93-4951-ad74-1953a22be098" />

**TripTalk**은 사용자의 여행 스타일과 선호도를 기반으로 AI가 최적의 여행 일정을 생성해주는 플랫폼입니다. 
FastAPI 기반 AI 서버와 연동하여 개인 맞춤형 여행 계획을 제공하며, 
실시간 항공권 및 숙소 정보를 조회하여 편리한 여행 준비를 지원합니다.

### 핵심 가치
- 🤖 **AI 맞춤 추천**: 사용자 취향 기반 여행 일정 자동 생성
- ✈️ **실시간 정보**: Amadeus API 연동 항공권 조회
- 🗺️ **커서 페이징**: 무한 스크롤 방식의 부드러운 UX
- 🔐 **보안**: JWT 기반 인증/인가 시스템

---

## ✨ 주요 기능

### 1. 🔐 사용자 인증
- **회원가입/로그인** (JWT Access & Refresh Token)
- **토큰 재발급** (Refresh Token 기반)
- **로그아웃** (Refresh Token 삭제)

### 2. 📱 메인 화면

- 여행 추천 카드 및 배너
- 인기 여행지 소개 (제주도, 부산, 파리, 뉴욕 등)
- 카테고리별 여행 상품 탐색
- AI 여행 플래너 바로가기
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/68fe8298-bf7f-48af-8d4e-032b4d3babed" />

### 3. 🗺️ AI 여행 일정 생성
- **FastAPI AI 서버 연동**
  - 여행 스타일 분석 (체험·액티비티, 자연, 힐링, 로컬 등 9가지)
  - 목적지, 기간, 예산, 동행인 기반 맞춤 일정 생성
- **일정 자동 저장**
  - 일별 상세 스케줄 (DailySchedule → ScheduleItem)
  - 교통편 정보 (출발/귀환)
  - 숙소 정보
  - 여행 하이라이트
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a951ba00-18d7-4da0-ad47-86a9f17c41e3" />

### 4. 📋 여행 계획 관리 (저장소)
- **상태별 조회** 
  - PLANNED (계획 중)
  - TRAVELED (여행 완료)
- **커서 기반 무한스크롤** (페이지 사이즈 5개)
- **여행 상태 변경** (PLANNED → TRAVELED)
- **여행 계획 상세 조회**
  - 전체 일정, 교통편, 숙소, 하이라이트 포함
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/30622c1b-8bbf-47ea-9d20-bd8407af553b" />

### 5. ✈️ 항공권 조회
- **Amadeus API 연동**
- **인기 노선 자동 조회**
  - 국내: 김포↔제주, 김포↔부산
  - 일본: 인천↔도쿄/오사카/후쿠오카 등
  - 기타: 방콕, 싱가포르, 홍콩, 뉴욕, 파리 등 20개 노선
- **주간 스케줄러** (매주 월요일 새벽 4시 자동 업데이트)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
- **국가별 대표 이미지 매핑**

### 6. 🏨 숙소 추천
- **추천 숙소 조회**
  - 18개 도시 × 3개 호텔
- **다양한 체크인/체크아웃 날짜** (오늘 +7~14일)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/99fcae83-5400-473b-ab52-bfaaa90d0de3" />

### 7. 🌴 여행지 추천
- **테마별 조회**
  - NATURE (자연)
  - SEA (바다)
  - CULTURE (문화)
  - HEALING (힐링)
  - HISTORY (역사)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/7256a998-fe15-4baa-b0aa-ae38d60071bb" />

### 8. 👤 사용자 정보 관리

- 개인 프로필 관리
- 여행 통계 (완료한 여행, 계획 중인 여행, 적립 포인트)
- 뱃지 시스템 (첫 여행, 시간 마니아, 한번가 등)
- 계정 설정 및 개인정보 변경
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/3d24365e-898b-4e1f-8f2e-8626720090c7" />

---

## 🛠 기술 스택

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.6
- **ORM**: Spring Data JPA (Hibernate 6.6.29)
- **Database**: MySQL 8.0 (AWS RDS)
- **Security**: Spring Security + JWT (jjwt 0.12.3)
- **API Docs**: Swagger (SpringDoc OpenAPI 2.7.0)
- **Build Tool**: Gradle 8.x

### External APIs
- **Amadeus API**: 실시간 항공권 정보 조회
- **FastAPI**: AI 기반 여행 일정 생성 서버

### Deployment
- **Server**: AWS EC2
- **Database**: AWS RDS (MySQL)
- **Container**: Docker

---

## 🏗 시스템 아키텍처

```
┌─────────────┐      ┌──────────────┐      ┌─────────────┐
│   Client    │ ───> │  Spring Boot │ ───> │   MySQL     │
│  (Mobile)   │      │  (REST API)  │      │   (RDS)     │
└─────────────┘      └──────────────┘      └─────────────┘
                            │
                            ├──────> FastAPI (AI 서버)
                            │
                            └──────> Amadeus API (항공권)
```

### 계층 구조 (Layered Architecture)
```
┌──────────────────────────────────────┐
│         Controller Layer             │  ← REST API 엔드포인트
├──────────────────────────────────────┤
│          Service Layer               │  ← 비즈니스 로직
├──────────────────────────────────────┤
│        Converter Layer               │  ← DTO ↔ Entity 변환
├──────────────────────────────────────┤
│       Repository Layer               │  ← DB 접근 (JPA)
└──────────────────────────────────────┘
```

---

## 🗂 ERD
<img width="1253" height="1726" alt="image" src="https://github.com/user-attachments/assets/39d34bc6-ce62-47a7-9acd-dafe829f3497" />

---

## 📡 API 명세

### Base URL
```
http://54.180.99.252:8080
```

### Swagger UI
```
http://52.78.55.147:8080/swagger-ui/index.html#/
```
---
## 🚀 설치 및 실행

### 요구사항
- Java 21
- MySQL 8.0
- Gradle 8.x

### 1. 프로젝트 클론
```bash
git clone https://github.com/your-repo/triptalk.git
cd triptalk
```

### 2. 데이터베이스 설정
```sql
CREATE DATABASE triptalk_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. 환경 변수 설정 (`application.yml`)
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/triptalk_db
    username: your-username
    password: your-password

jwt:
  secret: your-jwt-secret-key-at-least-32-characters
  access-token-expiration: 3600000  # 1시간
  refresh-token-expiration: 1209600000  # 2주

amadeus:
  api-key: your-amadeus-api-key
  api-secret: your-amadeus-api-secret
```

### 4. 빌드 및 실행
```bash
# 빌드
./gradlew clean build

# 실행
./gradlew bootRun

# 또는 JAR 파일 실행
java -jar build/libs/triptalk-0.0.1-SNAPSHOT.jar
```

## 🐳 Docker 실행

### Docker Compose
```bash
docker-compose up -d
```

## 📦 배포

### AWS EC2 배포

### 배포 환경
- **Server**: AWS EC2 (Amazon Linux 2)
- **Database**: AWS RDS MySQL 8.0
- **Domain**: 54.180.99.252:8080
- **Swagger**: http://54.180.99.252:8080/swagger-ui/index.html

---

## 🔧 주요 기술적 특징

### 1. 커서 기반 페이징 (Cursor-based Pagination)
- Offset 방식 대비 대용량 데이터 처리 성능 우수
- 실시간 데이터 변경에도 안정적
- 무한 스크롤 UX 최적화

### 2. JWT 인증/인가
- Access Token (1시간) + Refresh Token (2주)
- Stateless 인증 방식
- Spring Security FilterChain 적용

### 3. Converter 패턴
- DTO ↔ Entity 변환 로직 분리
- Service 계층 간결화
- 재사용성 및 유지보수성 향상

### 4. Enum 매핑
- 여행 스타일 한글 문자열 → Enum 자동 변환
- DB 정규화 및 타입 안정성 확보

### 5. Amadeus API 연동
- 실시간 항공권 가격 조회
- 주간 스케줄러 자동 업데이트
- 환율 자동 변환 (EUR/USD → KRW)

---

## 📂 프로젝트 구조

```
src/main/java/com/example/triptalk/
├── domain/
│   ├── user/
│   │   ├── controller/    # 사용자 관련 API
│   │   ├── service/       # 사용자 비즈니스 로직
│   │   ├── repository/    # 사용자 DB 접근
│   │   ├── entity/        # User, RefreshToken 엔티티
│   │   └── dto/           # 요청/응답 DTO
│   │
│   ├── tripPlan/
│   │   ├── controller/    # 여행 계획, 항공권, 숙소 API
│   │   ├── service/       # 여행 계획 생성/관리 로직
│   │   ├── converter/     # DTO ↔ Entity 변환
│   │   ├── repository/    # JPA Repository
│   │   ├── entity/        # TripPlan, DailySchedule 등
│   │   ├── dto/           # Request/Response DTO
│   │   ├── enums/         # TravelStyle, TripStatus
│   │   ├── scheduler/     # 항공권 자동 업데이트
│   │   └── util/          # 국가 이미지 매핑
│   │
│   └── tripPlace/
│       ├── controller/    # 여행지 추천 API
│       ├── service/       # 여행지 조회 로직
│       ├── repository/    # 여행지 DB 접근
│       └── entity/        # TripPlace 엔티티
│
├── global/
│   ├── apiPayload/        # 공통 응답 포맷
│   ├── config/            # Security, Swagger 설정
│   └── security/          # JWT 인증/인가
│
└── TriptalkApplication.java
```

---

**🌟 TripTalk과 함께 완벽한 여행을 계획하세요!**

