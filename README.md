## 헬스장 원정 도우미 GymEx Assist

주소를 입력하면 입력한 주소의 반경 20Km이내의 헬스장을 검색하여 거리순으로 5개를 보여주는 서비스입니다.
결과로 출력된 길찾기 url로 헬스장의 위치를 확인이 가능합니다.

## 프로젝트 기간
2024.03.07 ~ 2024.03.12

## stack

- Java 17
- Spring Boot 2.6.7
- Spring Data JPA
- Spring retry
- Redis
- Spock
- MariaDB
- Docker & Docker Compose
- Handlebars
- Testcontainers

## usecase

- 사용자는 별도의 회원가입 없이 서비스 이용이 가능하다.
- 자신의 위치 또는 이동하는 지역을 검색한다.
- 검색한 위치의 반경 20km이내의 헬스장이 거리순으로 5개 출력된다.
- 길안내 url을 클릭하여 헬스장의 위치와 경로를 확인한다.

## feature List

- 사용자에게서 도로명 또는 지번 주소를 입력받는다.
  - 정확한 주소를 요청하기 위해 Kakao 우편 번호 서비스를 이용한다.
  - 동과 호수는 제외한다. ex) "서울특별시 성동구 행당동 32-6"
    
- 입력 받은 주소를 위도, 경도로 변환한다.
  - 지구는 평면이 아니기에 구에서 두 점 사이의 최단 거리 구하는 공식이 필요하므로 haversine formula로 계산한다.
    
- 변환된 위도, 경도와 Kakao 키워드 검색 서비스를 이용하여 반경 20Km 이내의 헬스장을 추천 받는다.
  - 헬스장의 데이터는 길안내 URL로 제공받는다.
    
- 길안내 URL은 고객에게 제공 되기 때문에 가독성을 위해 shorten url로 제공 한다.
  - Base62를 통한 인코딩  


## example
<img width="1300" alt="스크린샷 2024-03-13 오후 3 39 48" src="https://github.com/Da-you/gym-navi/assets/108071860/aedae02e-b4d2-4bb6-b400-19eac28fb7cf">
<img width="1300" alt="스크린샷 2024-03-13 오후 3 40 18" src="https://github.com/Da-you/gym-navi/assets/108071860/4b6e352c-4946-4ba1-a498-2026b06e4a8d">


