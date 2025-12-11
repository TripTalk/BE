# 🌏 TripTalk - AI 기반 여행 계획 플랫폼

> FastAPI AI와 연동된 맞춤형 여행 일정 생성 및 관리 서비스

## 📋 목차
- [프로젝트 개요](#-프로젝트-개요)
- [주요 기능](#-주요-기능)
- [기술 스택](#-기술-스택)
- [시스템 아키텍처](#-시스템-아키텍처)
- [ERD](#-erd)
- [API 명세](#-api-명세)
- [설치 및 실행](#-설치-및-실행)
- [배포](#-배포)

---

## 🎯 프로젝트 개요

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

### 1. 🔐 사용자 인증 및 관리
- **회원가입/로그인** (JWT Access & Refresh Token)
- **토큰 재발급** (Refresh Token 기반)
- **로그아웃** (Refresh Token 삭제)
- **사용자 정보 관리**

### 2. 🗺️ AI 여행 일정 생성
- **FastAPI AI 서버 연동**
  - 여행 스타일 분석 (체험·액티비티, 자연, 힐링, 로컬 등 9가지)
  - 목적지, 기간, 예산, 동행인 기반 맞춤 일정 생성
- **일정 자동 저장**
  - 일별 상세 스케줄 (DailySchedule → ScheduleItem)
  - 교통편 정보 (출발/귀환)
  - 숙소 정보
  - 여행 하이라이트

### 3. 📋 여행 계획 관리 (저장소)
- **상태별 조회** 
  - PLANNED (계획 중)
  - TRAVELED (여행 완료)
- **커서 기반 무한스크롤** (페이지 사이즈 5개)
- **여행 상태 변경** (PLANNED → TRAVELED)
- **여행 계획 상세 조회**
  - 전체 일정, 교통편, 숙소, 하이라이트 포함

### 4. ✈️ 항공권 조회
- **Amadeus API 연동**
- **인기 노선 자동 조회**
  - 국내: 김포↔제주, 김포↔부산
  - 일본: 인천↔도쿄/오사카/후쿠오카 등
  - 기타: 방콕, 싱가포르, 홍콩, 뉴욕, 파리 등 20개 노선
- **주간 스케줄러** (매주 월요일 새벽 4시 자동 업데이트)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
- **국가별 대표 이미지 매핑**

### 5. 🏨 숙소 추천
- **하드코딩 기반 추천 숙소** (54개)
  - 18개 도시 × 3개 호텔
  - 실제 호텔명 사용 (신라호텔, 리츠칼튼 등)
- **다양한 체크인/체크아웃 날짜** (오늘 +7~14일)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
- **호텔별 고유 이미지**

### 6. 🌴 여행지 추천
- **테마별 조회**
  - NATURE (자연)
  - SEA (바다)
  - CULTURE (문화)
  - HEALING (힐링)
  - HISTORY (역사)
- **커서 기반 무한스크롤** (페이지 사이즈 10개)
- **국내/해외 여행지 100개** (하드코딩)
- **실제 여행지 이미지 URL**

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

### 주요 엔티티

#### 1. User (사용자)
```sql
- id (PK)
- email (UK)
- password
- nickname
- created_at, updated_at
```

#### 2. TripPlan (여행 계획)
```sql
- id (PK)
- title (VARCHAR 100)
- destination, departure (VARCHAR 50)
- start_date, end_date (DATE)
- companions (VARCHAR 50)
- budget (VARCHAR 30)
- img_url (VARCHAR 255)
- status (ENUM: PLANNED, TRAVELED)
- user_id (FK → User)
- travel_styles (ElementCollection)
```

#### 3. DailySchedule (일별 일정)
```sql
- id (PK)
- day (INT)
- date (DATE)
- trip_plan_id (FK → TripPlan)
```

#### 4. ScheduleItem (상세 일정)
```sql
- id (PK)
- order_index (INT)
- time (TIME)
- title (VARCHAR 50)
- description (VARCHAR 100)
- daily_schedule_id (FK → DailySchedule)
```

#### 5. TripTransportation (교통편)
```sql
- id (PK)
- origin, destination (VARCHAR 50)
- airline_name, name (VARCHAR 50)
- price (INT)
- trip_plan_id (FK → TripPlan)
```

#### 6. TripAccommodation (숙소)
```sql
- id (PK)
- name, address (VARCHAR 100)
- price_per_night (INT)
- trip_plan_id (FK → TripPlan)
```

#### 7. TripHighlight (하이라이트)
```sql
- id (PK)
- content (VARCHAR 100)
- trip_plan_id (FK → TripPlan)
```

#### 8. Flight (항공권 정보)
```sql
- id (PK, AUTO_INCREMENT)
- origin, destination (VARCHAR 50)
- airline_name (VARCHAR 50)
- departure_time, arrival_time (VARCHAR 20)
- price (INT)
- currency (VARCHAR 10)
- image_url (TEXT)
- created_at, updated_at (TIMESTAMP)
```

#### 9. Accommodation (추천 숙소)
```sql
- id (PK, AUTO_INCREMENT)
- hotel_name, city_name (VARCHAR 100)
- price_per_night (INT)
- check_in_date, check_out_date (DATE)
- image_url (TEXT)
- created_at, updated_at (TIMESTAMP)
```

#### 10. TripPlace (여행지)
```sql
- id (PK, AUTO_INCREMENT)
- place_name (VARCHAR 100)
- city, country (VARCHAR 50)
- description (TEXT)
- image_url (TEXT)
- themes (ElementCollection: ThemeType)
- created_at, updated_at (TIMESTAMP)
```

#### 11. RefreshToken (리프레시 토큰)
```sql
- id (PK)
- token (VARCHAR 255, UK)
- user_id (FK → User)
- expiry_date (TIMESTAMP)
```

### ER 다이어그램 관계
```
User 1 ──< * TripPlan
         └──< * RefreshToken

TripPlan 1 ──< * DailySchedule
           └──< * TripTransportation
           └──< * TripAccommodation
           └──< * TripHighlight

DailySchedule 1 ──< * ScheduleItem
```

---

## 📡 API 명세

### Base URL
```
http://54.180.99.252:8080
```

### Swagger UI
```
http://54.180.99.252:8080/swagger-ui/index.html
```

### 1. 인증 API (Auth)

#### 회원가입
```http
POST /api/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123",
  "nickname": "여행러버"
}
```

#### 로그인
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}

Response:
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc...",
  "nickname": "여행러버"
}
```

#### 토큰 재발급
```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "eyJhbGc..."
}

Response:
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc..."
}
```

#### 로그아웃
```http
POST /api/auth/logout
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "refreshToken": "eyJhbGc..."
}
```

---

### 2. 여행 계획 API (TripPlan)

#### FastAPI 생성 여행 계획 저장
```http
POST /api/trip-plan/from-fastapi
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "title": "제주 액티비티 탐험 5박 6일",
  "destination": "제주도",
  "departure": "서울",
  "startDate": "2025-12-10",
  "endDate": "2025-12-15",
  "companions": "친구",
  "budget": "70만원",
  "travelStyles": ["체험·액티비티", "자연과 함께"],
  "highlights": ["겨울 바다 만끽", "한라산 등반"],
  "dailySchedules": [...],
  "outboundTransportation": {...},
  "returnTransportation": {...},
  "accommodations": [...]
}
```

#### 저장소 조회 (상태별)
```http
GET /api/trip-plan/archive?status=PLANNED&cursorId={lastId}
Authorization: Bearer {accessToken}

Response:
{
  "tripPlanList": [...],
  "tripPlanListSize": 5,
  "isFirst": true,
  "hasNext": true,
  "nextCursorId": 123
}
```

#### 여행 계획 상세 조회
```http
GET /api/trip-plan/{tripPlanId}
Authorization: Bearer {accessToken}
```

#### 여행 상태 완료 처리
```http
PATCH /api/trip-plan/{tripPlanId}/traveled
Authorization: Bearer {accessToken}
```

---

### 3. 항공권 API (Flight)

#### 항공권 조회
```http
GET /api/flights?cursorId={lastId}

Response:
{
  "flightList": [
    {
      "id": 1,
      "origin": "김포",
      "destination": "제주",
      "airlineName": "대한항공",
      "departureTime": "2025-12-17 07:25",
      "arrivalTime": "2025-12-17 08:35",
      "price": 98340,
      "currency": "KRW",
      "imageUrl": "https://..."
    }
  ],
  "flightListSize": 10,
  "isFirst": true,
  "hasNext": true,
  "nextCursorId": 10
}
```

---

### 4. 숙소 API (Accommodation)

#### 추천 숙소 조회
```http
GET /api/accommodations?cursorId={lastId}

Response:
{
  "accommodationList": [
    {
      "id": 1,
      "hotelName": "서울 신라호텔",
      "cityName": "서울",
      "pricePerNight": 150000,
      "checkInDate": "2025-12-17",
      "checkOutDate": "2025-12-19",
      "imageUrl": "https://..."
    }
  ],
  "accommodationListSize": 10,
  "isFirst": true,
  "hasNext": true,
  "nextCursorId": 10
}
```

---

### 5. 여행지 API (TripPlace)

#### 테마별 여행지 조회
```http
GET /api/trip-places?theme=NATURE&cursorId={lastId}

Response:
{
  "tripPlaceList": [
    {
      "id": 1,
      "placeName": "한라산 국립공원",
      "city": "제주",
      "country": "대한민국",
      "description": "대한민국 최고봉...",
      "imageUrl": "https://...",
      "themes": ["NATURE", "HEALING"]
    }
  ],
  "tripPlaceListSize": 10,
  "isFirst": true,
  "hasNext": true,
  "nextCursorId": 10
}
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

### 5. 초기 데이터 삽입
```bash
# 항공권 데이터는 스케줄러가 자동 생성
# 숙소 데이터 삽입
mysql -u username -p triptalk_db < accommodation_data.sql

# 여행지 데이터 삽입
mysql -u username -p triptalk_db < trip_place_data.sql
```

---

## 🐳 Docker 실행

### Docker Compose
```bash
docker-compose up -d
```

### docker-compose.yml
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/triptalk_db
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db

  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: triptalk_db
    ports:
      - "3306:3306"
```

---

## 📦 배포

### AWS EC2 배포
```bash
# EC2 접속
ssh -i your-key.pem ec2-user@54.180.99.252

# Docker로 실행
docker build -t triptalk .
docker run -d -p 8080:8080 triptalk
```

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

## 👥 팀 정보

- **Backend Developer**: [Your Name]
- **AI Developer**: FastAPI 서버 담당
- **Frontend Developer**: Mobile App 담당

---

## 📝 라이센스

This project is licensed under the MIT License.

---

## 📧 문의

- **Email**: your-email@example.com
- **GitHub**: https://github.com/your-repo/triptalk
- **Swagger**: http://54.180.99.252:8080/swagger-ui/index.html

---

## 🔄 버전 히스토리

### v0.0.1 (2025-12-11)
- ✅ JWT 인증/인가 시스템 구현
- ✅ FastAPI 연동 여행 계획 생성
- ✅ 저장소 조회 (커서 페이징)
- ✅ Amadeus API 항공권 조회
- ✅ 추천 숙소 조회
- ✅ 여행지 추천 (테마별)
- ✅ AWS EC2 배포

---

**🌟 TripTalk과 함께 완벽한 여행을 계획하세요!**

