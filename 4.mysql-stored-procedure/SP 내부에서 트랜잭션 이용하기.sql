drop procedure if exists rollback_sp;

delimiter &&
create procedure rollback_sp(name varchar(255))
BEGIN 
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SELECT 'Error occurred, transaction rolled back' AS message;
	END;

	start transaction;
	select * from employee;
	insert into rollback_example(name) value ('tester1');
	insert into employee(name,grade) value('tester1', 'low');

	commit;

END&&
delimiter ;


call rollback_sp('tester1');

select count(*) from rollback_example; ## SP 이전과 동일한 카운트가 나온다.