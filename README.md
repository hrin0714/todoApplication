# todoApplication
 
 ### 개요 
  * Spring Boot, JQuery, H2, Mybatis 를 이용한 Todo Web Application 개발
 
 

 ### 준비
  * IntelliJ-ultimate, Git latest version 



 ### 실행방법
  * IntelliJ-ultimate 실행 > VCS > Check out from VersionControl > Git 실행
  * URL : https://github.com/hrin0714/todoApplication
  * Directory : 프로젝트가 저장될 디렉토리 설정
  * Clone 클릭 (본인의 gitHub 계정으로 로그인이 필요함)
  * Clone 중 pom.xml 실행여부 시 진행(proceed)함
  * Clone 이 완료되면 pom.xml에 명시된 라이브러리가 다운로드가 진행됩니다.
  * Run > Run'Application' 
    - Console에  Spring boot 실행 화면이 나오면 성공.
    - 본인의 브라우져에서 http://localhost:8080 이동합니다.
  


 ### 사용 설명
  * Todo 등록
    - 상단 Text Input창에 할일을 입력 후 '등록' 버튼을 누른다. 등록이 정상적으로 되면, 알림창과 테이블이 재정렬 된다.
      + 제약사항 : 1) 할일명은 중복이 될 수 없다.
                
             
  * Todo 수정
    - 일반수정 : 리스트의 할일 명을 클릭하면 해당 ROW의 내용을 수정할 수 있는 모달창이뜬다. 할일명과 참조 할일을 선택하여 하고, 수정버튼을 누르면, 알림창과 함께 테이블이 재정렬 된다.
      + 제약사항 : 1) 스스로를 참조는 불가하다.
                  2) 교차 참조는 불가하다. 단, 참조하고자 하는 Todo의 상태가 완료인 경우는 예외이다.
                           
                
    - 상태변경 : 리스트에서 우측 상태 셀렉트박스를 선택하여 상태를 변경한다.
      + 제약사항 : 상태값을 '완료'로 변경을 할때, 참조하고 있는 Todo 가 진행중이면 완료 처리가 되지 않는다.            
 
 
  * Todo 삭제
    - 리스트의 '삭제'를 클릭하면 해당 Todo가 삭제된다. 이때 해당Todo 와 함께, 참조하고 있는, 참조받고 있는 목록도 함께 삭제가 된다. 삭제가 되면 알림창과 함께 테이블이 재정렬 된다.
    
