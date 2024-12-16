create table employee(
	id int not null auto_increment primary key,
    name varchar(255) not null unique,
    grade enum('low' ,'middle' ,'high') not null
);

create table rollback_example(
	id int not null auto_increment primary key,
    name varchar(255)
);
