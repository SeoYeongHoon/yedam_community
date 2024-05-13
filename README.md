# 예담 수료생 전용 커뮤니티

> 담당

깃허브 관리 - 영훈
DB관리 - 원영
개발환경 구축 - 택준

> 담당 기능

cbt 학생 - 원영(교수 작업 진행되는 상황 보고 후에 붙어서 작업)
 
cbt 교수 - 택준

과제 학생 + 교수 - 영재

게시판 + 댓글 - 우열

회원가입+로그인+메인페이지+관리자페이지 - 영훈,원영

<hr>

>  최초 업무 프로세스
1. STS의 Git Repository에서 yedam_community 우클릭
2. Switch To
3. New Branch
4. Branch name에 자기 이름 영어로(예: 서영훈인 경우 SYH, 전영재인 경우 JYJ)
5. Commit
6. Push HEAD
7. 깃 허브에서 브랜치 생성되었는지 확인 후 작업 시작
8. 그 날 작업 완료 후 Git Staging에서 +/+ 로 전부 추가
9. Commit And Push. * Commit 메세지는 작업 한 것 한줄 요약. (ex: 메인페이지, 정보게시판 작성)
10. 깃 허브로 와서 Pull Requests 생성.
11. 텍스트란에, 제목의 한줄 요약을 조금 더 풀어서 설명. (ex: 정보게시판 기능 구현. 글작성, 수정, 삭제 기능 추가. 댓글기능 추가)
12. 충돌 없는지 확인하고 Main 브랜치에 Merge(충돌 있으면 일단 본인이 확인해서 고쳐보고 안 되면 팀장에게 말하기)
13. 본인 PC 환경에 맞게 application.properties 수정(hr 계정 비밀번호 등)

>  업무 프로세스(도중)
1. 전날에 조원들이 Main 브랜치에 Merge를 했으므로, 작업 시작 시 yedam_community 우클릭 -> Pull... 클릭
2. Reference에 main 입력(입력하면 main[branch] 라고 뜰 것이다) 후 Finish
3. 충돌 발생 시 해결하기. 만약 본인 작업이 아닌 다른 조원의 파일 때문에 충돌이 발생하면 !!바로바로!! 소통해서 해결.
<h3>!!절대로 main 브런치에서 작업 금지!!</h3> 
