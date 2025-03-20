create table message(
	idx int auto_increment primary key,
	sendName varchar(50),
	receiveEmail varchar(50),
	content varchar(2000),
	sendDate datetime default now()
);

select * from message;

insert into message(sendName, receiveEmail, content) 
values('test2','test','테스트메일입니다.');