delimiter //

drop function if exists test3 //
create function test3()
returns int 
reads sql data
return 1;
end//
delimiter ;

select * from student where student_id = test3();

delimiter //
drop function if exists mostCommonBirthYear //
create function mostCommonBirthYear()
returns int
reads sql data
return (select birth_year from student group by birth_year order by count(*) desc limit 1);
end//
delimiter ;

select mostCommonBirthYear();
select * from student where birth_year = mostCommonBirthYear();


