# 함수 삭제에 대한 예제입니다. 함수 삭제하는 방법에 대해서 배우고, 함수 삭제에서 발생할 수 있는 문제를 알아봅니다.
delimiter //
drop function if exists testA;
create function testA()
returns int
deterministic
return 1;
end//

delimiter ;

delimiter //
drop function if exists testB;
create function testB()
returns int
deterministic
return testA();
end//

delimiter ;
# drop 명령어를 이용해 함수를 삭제할 수 있습니다.
drop function testA;
# 하지만 삭제를 할 때에, 함수가 다른 곳에 참조된 경우 FK처럼 삭제 순간에 오류를 뱉지 않고, 다른 함수를 실행할 때 오류가 발생합니다.
select testB();
