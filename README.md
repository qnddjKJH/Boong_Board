# Spring Boot & JPA 게시판
Spring boot 와 JPA 공부했던 내용 종합적으로 간단하게 게시판 만들기

## Docker
Docker 를 기본 공부를 다시 하고 Docker 적용할 예정 지금은 실패..ㅠ

## stack
* jdk11
* spring
* jpa
* mySql - 현재는 개발 단계로 H2 사용

## 목표
 * [ ] CRUD 게시판 설계
   * 도메인 설계부터
     * [x] Post
     * [ ] Board
     * [ ] Member
 * [ ] 회원가입 기능 도입
   * 로그인
   * SNS 로그인
 * [ ] 외부 API 사용한 기능 추가
   * 아이디어 짜기
 * [ ] REST ful API 

### Todos
 * 도메인 설계 완성
   * [x] Post - 게시글
   * [ ] Board - 게시판
     * Post 와 양방향 작성 해보기
   * 목표에 맞게 계속해서 추가할 예정
 * 단위 테스트 하기
   * [x] Post - CRUD 완료
 * 게시판 뷰 작성
   * Post - 1.27
     * [x] 게시글 리스트 조회
     * [x] 게시글 작성 버튼 추가하기
     * [x] 게시글 조회 작성
     * [x] 게시글 수정 폼 만들기
     * [x] 게시글 삭제
     * [x] 게시글 게시판 분류
       * notice, free 만 있는데 나중에 생각나면 추가
   * Board
     * [ ] view 작성하기
       * 메뉴 or 드롭박스 or 등등 게시판 별로 게시글 출력
     * [ ] 연결 안된 곳 연결하기
   * Member
     * [ ] 회원가입
     * [ ] 로그인
       * SNS 로그인
       * 보안 적용까지
       * 로그인 유지
    
