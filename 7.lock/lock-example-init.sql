create table account(
	account_number varchar(255),
    account_owner_name varchar(255),
    balance bigint,
    version bigint,
    
	primary key(account_number)
);

insert into account values ('123-123-123-123', 'kim', 1000000000, 0) , ('123-123-123-124', 'sin', 1000000000, 0);