-- DQL & Functions --
-- Select statements
--select username, userpassword, authrole
-- from authusers 
-- where username = 'admin';

-- scalar function
--select upper(username) from authusers;

-- aggregate function
--select count(username) from authusers;

-- views
create view get_users as select username from authusers;

-- function
create function get_time() returns time with time zone as
$$
    select current_time;
$$ Language sql;

-- select get_password('username');
create function get_password(uname text) returns text as
$$
    select userpassword from authusers where username = uname;
$$ language sql;