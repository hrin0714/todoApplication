--
--    Copyright 2015-2017 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.

-- TODO리스트
drop table if exists k_todo_list;
create table k_todo_list ( todo_id int primary key  auto_increment,	what_todo   varchar(255), status char(1), update_date timestamp,insert_date timestamp, dbsts char(1));

insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (1,'골프연습',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (2,'젤다의전설하기',  	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (3,'면접준비',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (4,'술마시기',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (5,'선생님 면담',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (6,'영어공부는 필수라는데',	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (7,'등산은 바다로',		CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (8,'Python 책사러가기',	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (9,'페이징 처리 개발',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (10,'validation not work 해결',		CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (11,'인텔리J 사용법',      	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (12,'셀프셀렉트 미노출 제약',	CURRENT_TIMESTAMP(),'C', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (13,'서로 추가 금지(교차 참조 제약)',	CURRENT_TIMESTAMP(),'C', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (14,'Junit',	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (15,'GIT 설명서작성',	CURRENT_TIMESTAMP(),'I', 'N');
insert into k_todo_list (todo_id, what_todo, insert_date, status, dbsts) values (16,'과제제출',	CURRENT_TIMESTAMP(),'I', 'N');

-- 참조리스트
drop table if exists k_todo_ref_list;
create table k_todo_ref_list (ref_seq int primary key  auto_increment,todo_id int,parent_todo_id int,insert_date    timestamp); 
	       							
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (4, 1, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (4, 3, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (5, 2, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (6, 7, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 9, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 10, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 11, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 12, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 13, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 14, CURRENT_TIMESTAMP());
insert into k_todo_ref_list (todo_id, parent_todo_id, insert_date) values (16, 15, CURRENT_TIMESTAMP());


