# 예제에 대한 초기 설정 파일입니다. student 테이블을 생성한 후, 아래 데이터를 삽입해주세요.
# study라는 스키마를 이용합니다! 이미 해당 스키마가 존재한다면, sql문들을 확인하면서 수정해주세요!
drop schema if exists study;
create schema study;
use study;

create table student(
	student_id int auto_increment not null,
    name varchar(25),
    birth_year int,
    register_year int,
    gender char(6),
    primary key (student_id)
);

insert into student(name, birth_year, register_year, gender) values ('손흥민' , 1992, 2022, 'male'),
 ('김연아', 1990, 2020, 'female'),
 ('박지성' , 1981, 2024, 'male'),
 ('이재성', 1992, 2023, 'male');