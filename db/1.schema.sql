-- DDL --
-- drop table if exists authusers;

create sequence authusers_id_seq increment 3 minvalue 15 maxvalue 200000 start 20;

create table authroles (
    id serial primary key,
    authrole text
);

create table authusers (
    id integer not null default nextval('authusers_id_seq'::regclass),
    username text not null unique,
    userpassword text,
    authrole integer references authroles(id)
);
