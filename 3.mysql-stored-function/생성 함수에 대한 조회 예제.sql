# 생성된 함수에 대해서 알아봅니다. 루틴(함수와 프로시저)를 조회하는 방법은 아래와 같고, 일반 select문처럼 조건문에서 루틴이 생성된
# 스키마를 통해 조회하거나, 타입을 이용해 조회할 수 있습니다.
select * from information_schema.routines;

select * from information_schema.routines where routine_schema = 'study';