select * from information_schema.routines where ROUTINE_TYPE = 'PROCEDURE' and ROUTINE_SCHEMA = 'study'; ## 아래 두 것을 합친 내용 + a

show procedure status where Db = 'study'; ## DB이름, Procedure 이름, 타입 이름(프로시저인지 펑션인지), 작성자, 수정시간, 생성시간, 캐릭터셋 등등..

show create procedure register_employee; ## create 과정에서 작성된 내용 확인 가능 