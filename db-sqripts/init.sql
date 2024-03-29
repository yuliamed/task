CREATE DATABASE "spring-security" WITH
    OWNER = root
    ENCODING = 'UTF8';

-- create table "roles"
-- (
--     id   bigserial constraint roles_pkey PRIMARY KEY not null,
--     name varchar(64)           not null
-- );
--
-- create table "users"
-- (
--     id                  bigserial constraint users_pkey PRIMARY KEY not null,
--     email               varchar(64)           not null unique,
--     name                varchar(64),
--     surname             varchar(64),
--     pass                varchar(256),
--     creation_date       timestamp             not null,
--     last_update_date    timestamp             not null,
--     version             integer               not null,
--     ban_date            timestamp,
--     is_active           boolean default (false),
--     image_url           varchar(255),
--     last_visit_date     timestamp,
--     activation_token    varchar(255),
--     recovery_token      varchar(255),
--     token_creation_date timestamp
-- );
--
-- -- create table user_roles
-- -- (
-- --     user_id bigserial not null primary key,
-- --     role_id bigserial not null primary key,
-- --     CONSTRAINT "FK_user_id" FOREIGN KEY ("user_id")
-- --         REFERENCES "users" ("id"),
-- --     CONSTRAINT "FK_role_id" FOREIGN KEY ("role_id")
-- --         REFERENCES "roles" ("id")
-- -- );
--
-- create table user_roles
-- (
--     user_id bigserial not null
--         constraint fk2o0jvgh89lemvvo17cbqvdxaa
--             references users,
--     role_id bigserial not null
--         constraint fkj6m8fwv7oqv74fcehir1a9ffy
--             references roles,
--     constraint user_roles_pkey
--         primary key (user_id, role_id)
-- );
--
-- insert into roles (name)
-- values ('USER');
-- insert into roles (name)
-- values ('ADMIN');
--
-- insert into users ( email,
--                     name,
--                     surname,
--                     pass,
--                     creation_date,
--                     last_update_date,
--                     version,
--                     is_active)
-- values ( 'pass@mail.ru',
--          'admin',
--          'admin',
--          '$2a$10$0EThLXXElNCyZxBW3eRJAOoicfsvs4AhJeuEaS/CsL..nMNMieTai',--admin-1234
--          current_timestamp,
--          current_timestamp,
--          1,
--          true);
-- insert into "user_roles" (role_id, user_id)
-- values (2, 1);
-- select * from users;
-- select * from roles;
