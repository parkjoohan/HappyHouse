1. 요구사항 정의서<br />
   * 전체<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770163-1c3ad03e-6900-41f7-a129-6c54723bd704.png)
   * HappyHouseMain 관리<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770253-c1a3dd66-357f-4970-8dcb-427828a53fa5.png)
   * 사용자 관리<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770257-5a920c15-f9da-414a-b1d7-2e5064fcf885.png)
   * 게시판 관리<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770268-f8fefcc2-9d15-4de3-a153-b29f175bee6f.png)
   * 부동산 뉴스 관리<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770282-c3e8f970-1340-4a34-a1c9-9e20bf30beac.png)
<br /><br /><br /><br />
2. 테이블 구조도<br />
![image](https://user-images.githubusercontent.com/85006536/143770335-6cfcaadc-fa74-461c-8fd0-7fe56478f801.png)
<br /><br /><br /><br />
3. 클래스 다이어그램<br />
![image](https://user-images.githubusercontent.com/85006536/143770419-32b8fe65-53f9-42b9-b880-118561c4a2b8.png)
<br /><br /><br /><br />
4. 화면 설계서<br />
   * 메인 화면<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770496-843cca05-265c-49d9-9e03-f8cdf52cf0dc.png)<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770501-20852126-4106-4c39-98c4-c938adec69a6.png)<br />
        -	Header, footer 구현
        -	Header : 기능별 버튼 구현 / 대표 이미지 클릭 시 메인 페이지로 전환
        -	Footer : 기능별 버튼 구현 / 유용한 사이트 이동 버튼 구현 / 제작자 정보 (연락처, GITHUB)<br />
   * 회원기능<br />
     · 로그인<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770548-366d2b6e-4ce7-4914-abf8-3c81115e3d2c.png)<br />
     · 회원가입<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770579-63c89c90-64fd-4e2d-b32d-6825e935209b.png)<br />
        -	사용자 정보 조회하여 회원 중복 확인
        -	카카오 우편주소 API 사용하여 주소 세팅<br />
     · 내 정보 보기<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770725-e8054cd3-e0dc-44df-a789-bd2ab2cf2b0a.png)<br />
        -	로그인 된 아이디의 회원정보 확인 가능
        -	“수정하기”를 통해 회원 정보에 대한 수정 가능
        -	“회원삭제”를 통해 불필요한 회원은 탈퇴 가능<br />
   * 부동산 기능<br />
     · 부동산 정보<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770764-2cddfdfc-73fc-476d-aef3-343eb9e0fff2.png)<br />
        -	카카오 지도 API를 사용하여 키워드 검색 및 지도 변환 가능 (지도 뷰, 스카이뷰)
        -	여러 형태의 지도 정보 확인 가능 (지적편집도, 지형정보, 교통정보, 자전거도로)
        -	확인하고자 하는 지역을 선택하여 월별로 계약된 부동산 거래 내역들을 확인 가능
        -	공공데이터를 활용하여 선택한 지역에 설치된 CCTV의 위치를 확인 가능<br />
     · 지역 정보<br />
     ![image](https://user-images.githubusercontent.com/85006536/143770785-16c2256b-32ec-4976-ac04-da1ae3d17951.png)<br />
        -	공공데이터를 활용하여 확인하고자 하는 지역의 지역 정보들을 확인 가능
        -	지역 정보를 원 그래프를 통해 쉽게 확인 가능



