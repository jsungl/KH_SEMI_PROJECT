# 여기요(Yeogiyo)
![여기요 로고](https://i.esdrop.com/d/m2k8l3fm5y4u/JRoKBAMcMN.jpg)

2021.04.01 첫 MEETING <br>
팀선정후 첫 회의 <br>
팀장선출 : 김상훈 <br>
주제선정 : 음식배달 웹사이트 <br>
진행방법 : 클론코딩 <br>
참고사이트 : https://www.yogiyo.co.kr <br>

**사이트 구현 기능**
* 회원가입/회원탈퇴
* 로그인/로그아웃
* 상품주문(가게찾기)
* 리뷰
* 공지
* 관리자 페이지


**각자 구현할 기능 배분**
1. 회원가입/회원탈퇴, 회원테이블 - 황윤진
2. 메인(홈)화면 구현, 로그인/로그아웃, 아이디/비밀번호 찾기, 오늘의가게1개 추천페이지, 가게검색 - 이재성
3. 상품주문(가게찾기), 주문테이블 - 1,2개 추가해보고 api로 for문돌려서 넣기 - 김영미
4. 리뷰, 리뷰테이블 - 김상훈
5. 관리자페이지, 공지, 가게테이블 - 리뷰삭제기능 - 송준호

<br>

**깃허브 협업을 위한 레파지토리, 브런치(main, 팀원) 생성**
<br>
https://github.com/ksh940911/KH_SEMI_PROJECT/commits/main

**배포링크**
<br>
http://kym9129.ddns.net:10000/yeogiyo/

**기획발표 ppt 링크(docs)**
<br>
https://docs.google.com/presentation/d/1VZlXShFJVXbhDd2DAiqH-THgSKq_0X6Hb7UEvx__OL4/edit#slide=id.gcf4b52a444_8_21

**최종발표 ppt 링크(docs)**
<br>
https://docs.google.com/presentation/d/1SyP9CpOe9ELimd28vuUc91Wc7w_w-39qh9Y1_v69Fbc/edit

**최종 발표영상**
<br>
https://www.youtube.com/watch?v=zwLfqAhej4U

***

### 사용자 관점 

* 메인페이지

  * RANDOM버튼 클릭 -> 랜덤으로 가게1개 추천

  * 카테고리 클릭 -> 해당 카테고리별 가게리스트 출력

  * ※전체보기 -> 전체 가게리스트 출력

* 가게 리스트페이지

  * 가게리스트 정렬(평점순, 리뷰많은순, 최소주문금액순)

  * 가게검색(가게명)

* 가게 상세페이지

  * 주문표에 메뉴 담기(개수 추가,삭제), 전체삭제

  * 주문하기

* 주문 페이지

  * 결제수단 선택 -> 카카오페이,신용카드,카드,현금결제

  * 결제하기

* 마이페이지

  * 내정보 수정

  * 비밀번호 변경

  * 주문내역

  * 리뷰조회/등록/수정/삭제

  * 회원탈퇴

* 로그인페이지

  * 아이디/비밀번호 찾기

  * 회원가입

***

### 관리자 관점

* 회원관리

  * 회원조회 -> 아이디,회원명,성별

* 가게관리

  * 가게조회 -> 가게명

* 가게등록
