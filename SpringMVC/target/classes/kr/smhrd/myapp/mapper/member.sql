create table member(
	email varchar(50) primary key,
	pw varchar(50),
	tel varchar(50),
	address varchar(200)
);

insert into member values('test','1234','010-1234-1234','광주 서구');

select * from member;


select * from member where email != 'admin';

-- board 테이블 생성
create table board(
	idx int auto_increment primary key,
	title varchar(100) not null,
	writer varchar(100) not null,
	filename varchar(100),
	content varchar(2000) not null,
	b_date datetime default now()
);

-- test 데이터 삽입
insert into board(title, writer, filename, content)
values('test1', 'test1', 'test1', 'test1');
insert into board(title, writer, filename, content)
values('test2', 'test2', 'test2', 'test2');
insert into board(title, writer, filename, content)
values('test3', 'test3', 'test3', 'test3');

-- 조회
select * from board;




