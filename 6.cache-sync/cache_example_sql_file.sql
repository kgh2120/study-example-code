create schema study_db;
use study_db;


create table member(
	id bigint auto_increment,
	name varchar(100),
	age int,
	status_message varchar(255),
	
	primary key (id)

);


insert into member(name, age, status_message) values ('손흥민', 33, '안녕하세요~'), ('이강인' ,24 '행복하세요~'), ('김민재', 30, '하우스키핑');

insert into member(name, age, status_message) values ('손흥민', 33, '안녕하세요~'), ('이강인' ,24 '행복하세요~'), ('김민재', 30, '하우스키핑');

insert into member(name, age, status_message) values ('Son', 33, 'Hello world~'), ('Lee' ,24, 'Happy~'), ('Kim', 30, 'House Keeping');
