# PUK-Groupware
 - 부경대 빅데이터를 이용한 자바 개발자 양성 과정 2차 팀 프로젝트 - 크라우드 펀딩 웹 어플리케이션 제작
 - Spring Boot 3.3.0 ver, JPA 사용

## 개발 환경
### Languages
<div style="display:flex; flex-direction:column; align-items:flex-start;">
    <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
    <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-square&logo=javascript&logoColor=black">
    <img src="https://img.shields.io/badge/html5-E34F26?style=flat-square&logo=html5&logoColor=white">
    <img src="https://img.shields.io/badge/css-1572B6?style=flat-square&logo=css3&logoColor=white">
</div>

### Database
<div style="display:flex; flex-direction:column; align-items:flex-start;">
    <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> 
</div>

### Tools
<div style="display:flex; flex-direction:column; align-items:flex-start;">
    <img height="30" src="https://img.icons8.com/?size=100&id=9OGIyU8hrxW5&format=png&color=000000" title="vscode">
    <img height="30" src="https://img.icons8.com/?size=100&id=OkBCty7GwbXX&format=png&color=000000" title="dbeaver">
</div>


## 구현 목표
- 회원관리
    - 회원가입
    - 로그인
    - 회원정보 수정
    - 회원 탈퇴
- 게시물 관리
    - 프로젝트(상품)
        - 프로젝트 생성
        - 프로젝트 정보 조회
        - 프로젝트 참여
        - 프로젝트 정보 수정
        - 후원 금액에 따른 진행 상태 변경(진행중, 완료 등)
        - 각 프로젝트의 상세 페이지에 해당 프로젝트에 대한 회원들의 댓글 기능 구현
    - 게시판(공지사항, QnA)
        - 공지사항 게시판
            - 사이트 관리자의 회원을 대상으로 한 공지용 페이지
            - 공지사항 내 게시물 작성은 관리자 계정으로만 가능하도록 제한
            - 일반 회원(후원자, 제작자)은 공지사항 내 게시물 조회만 가능(생성, 수정, 삭제 불가)
        - QnA 게시판
            - 회원의 요구사항 및 질의 사항에 대한 관리자의 답변을 제공하는 페이지
            - 모든 회원이 게시물 생성 가능
            - 본인이 작성한 게시물에 한해 수정, 삭제 가능
            - 게시물 제목을 클릭하면 게시물 상세 페이지로 이동, 상세 페이지에서 댓글 기능 구현
            - 상세 페이지의 댓글 기능은 관리자 계정만 사용 가능하도록 구현

## 난관
- Servlet, JSP의 기본 개념만을 토대로 시작한 프로젝트였기에 계획 초반부 다소 무리라는 생각이 전반적
- JDBC 이외의 DB 연동을 적용해 본 적이 전무하여 새로 학습해야 할 부분 추가
- 팀 프로젝트, Git branch에 대한 개념 확립 부족
- 착수 이전의 기획 단계에 대한 정보 부족

## 문제 해결
- 프로젝트 전반
    - Spring FrameWork, JPA에 관한 전반적인 학습이 마쳐진 상황에서 진행 된 프로젝트가 아니라는 점
    - 학습과 제작의 병행에서 오는 어려움 다수 발생
    - 팀원 간 개별 학습, 정보 공유를 통해 대부분의 문제 해결
    - 지속적인 짧은 회의와 소통을 통해 각자 맡은 기능의 코드 리뷰, 주석을 통해 코드 전반에 대한 이해도 고취
- 버전 관리
    - 공동 코드, 개선사항 관리 등을 위한 도구로 Git 사용
    - Git 사용법에 대한 지식이 부족한 상태로 진행되었다보니 main branch에 commit을 하는 등의 실수로 데이터 손실 발생
    - 백업해 둔 코드와 GitHub History를 토대로 복구 완료
    - 이해도가 상대적으로 높았던 조원의 설명을 바탕으로 개념 확립
- 기획, 착수 초기단계
    - 요구사항, 데이터베이스 등 명세서와 같은 문서에 대한 개념이 정립되어있지 않아 더 많은 회의와 소통이 필요하였으며, 적지 않은 불편을 겪음
    - Spring Boot, GitHub등을 사용함에 있어 Eclipse의 원인 미상의 오류가 잦아 Visual Studio Code로 변경
    - 프로젝트 전체적 기능 측면에서의 데이터베이스 테이블 구성이 완료되지 않은 상태로 진행되어 도중에 테이블이 신규 생성되고, 테이블/컬럼 간 관계를 설정하는 등 순서를 거슬러 올라가는 경우도 발생
    - 모든 사항들을 차례대로 완벽히 정리하기에는 많은 어려움이 있을것이라 판단되어 최소한의 기능적 부분에 초점을 두고 조금씩 확장해가는 개념으로 접근하여 시작에 대한 막연한 어려움과 걱정 해결
 

 ### 프로젝트를 통해 느낀 점
 - 기획과 초기 설정에 대한 중요성과 프로젝트의 방향성을 제시하고 조정하는 리더의 부재가 가져오는 어려움에 대한 성찰
 - 적극적인 의견 제시와, 원활한 소통의 중요성
 - 기타 이론 및 학습에 대한 사항들

 ### 향후 개선사항 및 희망사항
 - JPA -> Mybatis 전환
 - 시각적 요소 보완
 - DB 성능 개선
 - 상용 서버를 이용한 실제 구현 가능한 웹 페이지 제작
 - API 사용을 통한 완성도 향상
 - 기획, 착수, 테스트, 배포 정석 단계의 신규 프로젝트
