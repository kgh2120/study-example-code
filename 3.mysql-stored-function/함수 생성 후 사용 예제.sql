delimiter //
drop function if exists convertGenderToKorean//
create function convertGenderToKorean(gender char(6))
returns char(4)
reads sql data
begin
    declare gender_korean char(4);

    if gender = 'male' then 
		SET @gender_korean = '남';
	elseif gender ='female' then 
		SET @gender_korean ='여';
	else
		SET @gender_korean ='알수없음';
	end if;
    return @gender_korean;
end//

delimiter ;


select name as '이름' , birth_year as '출생년도' , register_year as '입학년도' , convertGenderToKorean(gender) as '성별' 
from student;