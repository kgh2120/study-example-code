DELIMITER &&
DROP PROCEDURE IF exists TEST_PROCEDURE&& 
CREATE procedure TEST_PROCEDURE (inout age int)
SELECT age ;
set age = 3;
END&&
DELIMITER ;


set @age = 0;
call TEST_PROCEDURE(@age); ## 0
select @age; ## 0

DELIMITER &&
DROP PROCEDURE IF exists TEST_PROCEDUREE&& 
CREATE procedure TEST_PROCEDUREE (inout age int)
set age = 3;
SELECT age ;
END&&
DELIMITER ;


set @age = 0;
call TEST_PROCEDUREE(@age); ## select가 나오지 않음. 
select @age; ## 3

