DELIMITER &&
DROP PROCEDURE IF exists TEST_PROCEDURE2&& 
CREATE procedure TEST_PROCEDURE2 (in age int)
BEGIN
SELECT age ;
set age = 10;
END&&
DELIMITER ;

set @c = 3;
call TEST_PROCEDURE2(@c); ## 결과 3
select @c; ## 결과 3

DELIMITER &&
DROP PROCEDURE IF exists TEST_PROCEDURE3&& 
CREATE procedure TEST_PROCEDURE3 (out age int)
BEGIN
SELECT age ;
set age = 10;
END&&
DELIMITER ;


set @a = 1;
call TEST_PROCEDURE3(@a); ## 결과 null
select @a; ## 결과 10

DELIMITER &&
DROP PROCEDURE IF exists TEST_PROCEDURE4&& 
CREATE procedure TEST_PROCEDURE4 (inout age int)
BEGIN
SELECT age ;
set age = 20;
END&&
DELIMITER ;

set @b = 2;
call TEST_PROCEDURE4(@b); ## 결과 2
select @b; ## 결과 20
