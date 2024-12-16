drop procedure register_employee;

delimiter &&

create procedure register_employee(in name varchar(255), in password varchar(255), in grade enum('low' ,'middle', 'high'), out id int)
begin
	
    declare pwd varchar(255);
    set pwd = password;
    insert into employee(name, grade) value( name, grade);
    SELECT LAST_INSERT_ID() into id;
    
    set @create_sql = CONCAT('CREATE USER \'', name, '\'@\'localhost\' ',  'IDENTIFIED BY \'', password, '\';');
    select @create_sql;
    prepare create_statement from @create_sql;
    execute create_statement;
    deallocate prepare create_statement;
end &&
delimiter ;


set @last_id = 0;
call register_employee('tester1' ,'1q2w3e4r' , 'low', @last_id);
select @last_id;
delete from employee where id >= 1;

SELECT User, Host FROM mysql.user;

