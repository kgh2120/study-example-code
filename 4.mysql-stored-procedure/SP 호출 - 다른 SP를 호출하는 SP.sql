
delimiter &&
create procedure use_other_procedure(out age int)
	begin 
		declare test_age int;
        set test_age = 10;
		call TEST_PROCEDURE4(test_age);
        set age = test_age;
	end &&
delimiter ;


set @test_val = 5;
call use_other_procedure(@test_val); ## 결과 10
select @test_val; ## 결과 20