CREATE DATABASE "spring-security" WITH
    OWNER = root
    ENCODING = 'UTF8';

create table "roles"
(
    id   bigserial PRIMARY KEY not null,
    name varchar(64)           not null
);

create table "users"
(
    id                  bigserial PRIMARY KEY not null,
    email               varchar(64)           not null unique,
    name                varchar(64),
    surname             varchar(64),
    pass                varchar(256),
    creation_date       timestamp             not null,
    last_update_date    timestamp             not null,
    version             integer               not null,
    ban_date            timestamp,
    is_active           boolean default (false),
    image_url           varchar(255),
    last_visit_date     timestamp,
    activation_token    varchar(255),
    recovery_token      varchar(255),
    token_creation_date timestamp
);

create table user_roles
(
    user_id bigserial not null primary key,
    role_id bigserial not null primary key,
    CONSTRAINT "FK_user_id" FOREIGN KEY ("user_id")
        REFERENCES "users" ("id"),
    CONSTRAINT "FK_role_id" FOREIGN KEY ("role_id")
        REFERENCES "roles" ("id")
);

insert into roles (name)
values ('USER');
insert into roles (name)
values ('ADMIN');
insert into users (id, email,
                   name,
                   surname,
                   pass,
                   creation_date,
                   last_update_date,
                   version,
                   is_active)
values (1, 'adminmail@mail.ru',
        'admin',
        'admin',
        'admin-1234',
        current_timestamp,
        current_timestamp,
        1,
        true);
insert into "user_roles" (role_id, user_id)
values (2, 1);



