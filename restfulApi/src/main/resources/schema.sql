insert into user_details(id,name,birth_date)
values(1001, 'java', current_date());

insert into user_details(id,name,birth_date)
values(1002, 'jpa', current_date());

insert into user_details(id,name,birth_date)
values(1003, 'spring', current_date());

insert into post(id,description,user_id)
values(1, 'hi this is java', 1001);

insert into post(id,description,user_id)
values(2, 'hi this is  java 2', 1001);

insert into post(id,description,user_id)
values(3, 'hi this is spring', 1003);

insert into post(id,description,user_id)
values(4, 'hi this is spring2', 1003);